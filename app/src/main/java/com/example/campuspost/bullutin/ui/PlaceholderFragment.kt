package com.example.campuspost.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.campuspost.R
import com.example.campuspost.bullutin.data.Bullutin
import com.example.campuspost.bullutin.ui.BullutinViewModel
import com.example.campuspost.bullutin.ui.PageViewModel
import com.example.campuspost.bullutin.utility.BullutinInjectorUtils
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

/**
 * A placeholder fragment containing a simple view.
 */


class PlaceholderFragment : Fragment(), RecyclerViewHolderBullutin.ItemClickListener {

    private lateinit var pageViewModel: PageViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    var posts = mutableListOf<Bullutin>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
        initializeUI()
    }

    fun initializeUI() {
        val faculty = BullutinInjectorUtils.provideBullutinViewModelFaculty()
        val viewModel = ViewModelProvider(this, faculty)
            .get(BullutinViewModel::class.java)

        viewModel.getPosts().observe(this, Observer {
            val bullutinRecyclerView = view?.findViewById<RecyclerView>(R.id.recycleview_bullutin)!!
            viewAdapter = RecyclerAdapterBullutin(view?.context!!, this, posts)
            recyclerView = bullutinRecyclerView.apply {
                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                setHasFixedSize(true)

                // use a linear layout manager
                layoutManager = viewManager

                // specify an viewAdapter (see also next example)
                adapter = viewAdapter
                posts = it as MutableList<Bullutin>
            }
        })
    }

    fun addPost(post: Bullutin) {
        val faculty = BullutinInjectorUtils.provideBullutinViewModelFaculty()
        val viewModel = ViewModelProvider(this, faculty)
            .get(BullutinViewModel::class.java)
        viewModel.addPost(post)
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)

        viewAdapter = RecyclerAdapterBullutin(view?.context!!, this, posts)
        viewManager = LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)

        val bullutinRecyclerView = view.findViewById<RecyclerView>(R.id.recycleview_bullutin)
        recyclerView = bullutinRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        bullutinRecyclerView.addItemDecoration(DividerItemDecoration(view?.context, DividerItemDecoration.VERTICAL))

        return view

    }





    override fun onItemClick(view: View, position: Int) {
//        Toast.makeText(applicationContext, "position $position was tapped", Toast.LENGTH_SHORT).show()
    }


    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}




// swiftで言うと、tableViewのextensionのところ
class RecyclerAdapterBullutin(private val context: Context, private val itemClickListener: RecyclerViewHolderBullutin.ItemClickListener, private val itemList:List<Bullutin>) : RecyclerView.Adapter<RecyclerViewHolderBullutin>() {

    private var mRecyclerView : RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mRecyclerView = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolderBullutin {
        val layoutInflater = LayoutInflater.from(context)
        val mView = layoutInflater.inflate(R.layout.bullutin_board_cell, parent, false)

        mView.setOnClickListener { view ->
            mRecyclerView?.let {
                itemClickListener.onItemClick(view, it.getChildAdapterPosition(view))
            }
        }

        return RecyclerViewHolderBullutin(mView)
    }


    override fun onBindViewHolder(holder: RecyclerViewHolderBullutin, position: Int) {
        holder?.let {


            it.postMessage.text = itemList[position].message
            it.postTime.text = itemList[position].time
            it.userName.text = itemList[position].name
//            it.userPicuture.setImageURI(null)
//            it.userPicuture.setImageURI(Uri.parse("https://www.google.com/url?sa=i&url=https%3A%2F%2Ftwitter.com%2Fminami373hamabe&psig=AOvVaw0tzCDl99uTtf2aV0JMOGKt&ust=1620362756287000&source=images&cd=vfe&ved=0CAoQjRxqFwoTCJi7juaftPACFQAAAAAdAAAAABAD"))
            it.goodCountLabel.text = "${itemList[position].good}"
            it.repeatCountLabel.text = "${itemList[position].remessage}"
            it.commentCountLabel.text = "${itemList[position].comment}"

            if (itemList[position].photoUrl == null) {
                it.photoContainer.visibility = View.GONE
            } else {

            }

            if (itemList[position].isRemessagePostId == null) {
                it.remessageContainer.visibility = View.GONE
            } else {

            }

        }
    }


    override fun getItemCount(): Int {
        return itemList.size
    }


}



// swiftのtableViewCellの部分
class RecyclerViewHolderBullutin(view: View) : RecyclerView.ViewHolder(view) {

    // 独自に作成したListener
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int) {
            Log.d("viewHolder(cell)", "tapped")
        }
    }

    var userPicuture: ImageView = view.findViewById(R.id.imageview_bullutin_main)
    var userName: TextView = view.findViewById(R.id.username_textview_bullutin)
    var postTime: TextView = view.findViewById(R.id.posttime_textview_bullutin)
    var postMessage: TextView = view.findViewById(R.id.post_message_bullutin)
    val photoContainer: LinearLayout = view.findViewById(R.id.image_container_bullutin)
    val photo1: ImageView = view.findViewById(R.id.image1_bullutin)
    val photo2: ImageView = view.findViewById(R.id.image2_bullutin)
    val photo3: ImageView = view.findViewById(R.id.image3_bullutin)
    val photo4: ImageView = view.findViewById(R.id.image4_bullutin)
    val remessageContainer: View = view.findViewById(R.id.repeat_container_bullutin)
    val goodButton: ImageButton = view.findViewById(R.id.goodbutton_bullutin)
    val goodCountLabel: TextView = view.findViewById(R.id.goodcount_bullutin)
    val repeatButton: ImageButton = view.findViewById(R.id.repeatbutton_bullutin)
    val repeatCountLabel: TextView = view.findViewById(R.id.repeatcount_bullutin)
    val commentButton: ImageButton = view.findViewById(R.id.commentbutton_bullutin)
    val commentCountLabel: TextView = view.findViewById(R.id.commentcount_bullutin)
    val otherButton: ImageButton = view.findViewById(R.id.otherbutton_bullutin)

    init {
        // layoutの初期設定する
    }

}