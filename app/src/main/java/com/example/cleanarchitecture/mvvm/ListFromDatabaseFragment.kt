package com.example.cleanarchitecture.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecture.adapters.PersonListAdapter
import com.example.cleanarchitecture.constants.Constants
import com.example.cleanarchitecture.databinding.FragmentListFromDatabaseBinding
import com.example.cleanarchitecture.recyclerViewExt.SwipeToDeleteCallback
import com.example.cleanarchitecture.recyclerViewExt.addSpaceDecoration
import com.example.domain.model.LceState
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFromDatabaseFragment : Fragment() {

    private var _binding: FragmentListFromDatabaseBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val viewModel by viewModel<ListFromDatabaseViewModel>()

    private val adapter by lazy {
        PersonListAdapter(
            itemClick = {
                findNavController().navigate(
                    ListFromDatabaseFragmentDirections.actionListFromDatabaseFragmentToDetailsFragment(
                        it.name, it.nickname, it.birthday, it.status, it.img
                    )
                )
            },
            longItemClick = {
                Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentListFromDatabaseBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            viewModel.lceDatabaseFlow
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .onEach { lce ->
                    binding.progressCircular.isVisible = lce == LceState.Loading
                    when (lce) {
                        is LceState.Content -> {
                            lce.persons.collect {
                                adapter.submitList(it)
                            }
                        }
                        is LceState.Error -> {
                            Snackbar.make(
                                view,
                                lce.throwable.message ?: "State_Error",
                                Snackbar.LENGTH_LONG
                            )
                                .show()
                        }
                        LceState.Loading -> {
                        }
                    }
                }
                .launchIn(viewLifecycleOwner.lifecycleScope)

            val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val personToDel = adapter.currentList[viewHolder.adapterPosition]
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewModel.onPersonSwiped(personToDel)
                    }
                }
            }
            ItemTouchHelper(swipeToDeleteCallback).attachToRecyclerView(recyclerView)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.addSpaceDecoration(Constants.BOTTOM_SPACE)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
