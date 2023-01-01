package com.dev.spread.fragments.influencer

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.amintopup.base.viewGone
import com.dev.amintopup.base.viewVisible
import com.dev.spread.R
import com.dev.spread.databinding.FragSearchBinding


class SearchFragment : Fragment() {
    private var _binding: FragSearchBinding? = null
    private val binding get() = _binding!!

    private var Adapter: SearchAdapter? = null
    private var list = ArrayList<Model>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragSearchBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topbar.tvText.text = "Search"
        binding?.input?.etInput?.inputType = InputType.TYPE_CLASS_TEXT
        binding?.input?.etInput?.imeOptions = EditorInfo.IME_ACTION_DONE
        binding?.input?.etInput?.setImeActionLabel("Search", EditorInfo.IME_ACTION_DONE)
        binding?.input?.etLabel?.hint = "Search"
        binding?.input?.rlField?.setBackgroundResource(R.drawable.bg_serach)
        binding?.input?.ivImage?.setImageResource(R.drawable.iv_search)
        binding.input.etInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // Call onDone result here
                binding.input.ivImage.performClick()
                true
            }
            false
        }

        binding.input.ivImage.setOnClickListener {
            binding.rlinputfield.viewGone()
            binding.rlcenter.viewGone()
            binding.rlLabel.viewVisible()
            binding.rvSearch.viewVisible()
        }

        binding.topbar.ivBackArrow.setOnClickListener {
            findNavController().navigateUp()
        }


        setData()
        setRecycler()


    }

    private fun setRecycler() {
        Adapter = SearchAdapter(requireContext() as AppCompatActivity, list)
        binding?.rvSearch?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvSearch?.adapter = Adapter
    }

    private fun setData() {
        list.clear()
//        list.add(Model(R.drawable.dwaynejohnson,"Cameron W.","133,000 M","(5.0)"))
        list.add(Model(R.drawable.iv1, "Cameron W.", "133,000 M", "(5.0)"))
        list.add(Model(R.drawable.iv2, "Arlene M.", "133,000 M", "(5.0)"))
        list.add(Model(R.drawable.iv3, "Dianne R.", "133,000 M", "(5.0)"))
        list.add(Model(R.drawable.iv1, "Cameron W.", "133,000 M", "(5.0)"))
        list.add(Model(R.drawable.iv2, "Arlene M.", "133,000 M", "(5.0)"))
        list.add(Model(R.drawable.iv3, "Dianne R.", "133,000 M", "(5.0)"))


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}