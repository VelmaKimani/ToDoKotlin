package com.velma.todo.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.velma.todo.R
import com.velma.todo.activity.AddNoteActivity
import com.velma.todo.adapters.NotesAdapter
import com.velma.todo.databinding.FragmentNoteBinding
import com.velma.todo.model.Note
import com.velma.todo.model.User
import com.velma.todo.utils.Constants
import kotlinx.android.synthetic.main.fragment_note.*


/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class NoteFragment : Fragment(), NotesAdapter.ItemClickListener {

    private lateinit var binding: FragmentNoteBinding
    private val _binding get() = binding!!
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (requireActivity().intent != null) {
            user = requireActivity().intent.getParcelableExtra(Constants.USER)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        initViews()
        return _binding.root
    }

    private fun initViews() {
        binding.apply {
            val fullname = "Hello ${user.name}"
            tvHello.text = fullname
            tvHello.setOnClickListener{toProfilePage()}
            tvAddNotes.setOnClickListener { toTheAddPage() }
            val noteAdapter = NotesAdapter(requireContext(), this@NoteFragment)
            noteAdapter.addNotes()
            val layoutManger = LinearLayoutManager(requireActivity())
            layoutManger.orientation = VERTICAL
            notesRecyclerView.apply {
                layoutManager = layoutManger
                setHasFixedSize(true)
                adapter = noteAdapter
            }
        }
        /*notesRecyclerView.layoutManager = layoutManger
        notesRecyclerView.adapter = noteAdapter
    }*/
    }

    private fun toProfilePage() {
        val navController = Navigation.findNavController(binding.root)
        val args = Bundle()
        args.putParcelable(Constants.USER, user)
        navController.navigate(R.id.action_noteFragment_to_profileFragment, args)
    }

    private fun toTheAddPage() {
        //fragment to activity therefore use intent
        //activity to activity
        /* val intent = Intent(requireActivity(), AddNoteActivity::class.java)
         startActivity(intent)*/
        startActivity(Intent(requireActivity(), AddNoteActivity::class.java))
    }

    override fun itemClicked(view: View, note: Note) {
        if (view.id == R.id.noteCardView){
            startActivity(Intent(requireActivity(), AddNoteActivity::class.java))
        }

    }
}
