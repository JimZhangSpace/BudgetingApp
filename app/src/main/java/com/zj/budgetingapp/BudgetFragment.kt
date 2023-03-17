package com.zj.budgetingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.zj.budgetingapp.adapter.BudgetAdapter
import com.zj.budgetingapp.databinding.FragmentBudgetBinding
import com.zj.budgetingapp.repo.ExchangeRepoImpl
import com.zj.budgetingapp.vm.BudgetViewModel
import com.zj.budgetingapp.vm.BudgetViewModelFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class BudgetFragment : Fragment() {

    private var _binding: FragmentBudgetBinding? = null

    private val binding get() = _binding!!

    private val application: TheApplication by lazy { requireActivity().application as TheApplication }
    private val transactionAdapter:BudgetAdapter by lazy { BudgetAdapter() }

    private val budgetViewModel: BudgetViewModel by viewModels {
        BudgetViewModelFactory(
            application.budgetRepo,
            ExchangeRepoImpl()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBudgetBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.adapter = transactionAdapter
        budgetViewModel.allBudget.observe(this) {list->
           transactionAdapter.submitList(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}