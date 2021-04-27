package top.z7workbench.bjutloginapp.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.MaterialContainerTransform
import top.z7workbench.bjutloginapp.R
import top.z7workbench.bjutloginapp.databinding.LanguagesBinding
import top.z7workbench.bjutloginapp.databinding.RecyclerItemBinding

class LocaleFragment : BasicFragment<LanguagesBinding>() {
    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        LanguagesBinding.inflate(inflater, container, false)

    override fun initViewAfterViewCreated() {
        binding.recyclerToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.recycler.run {
            adapter = LocalesAdapter(resources.getStringArray(R.array.language).toList())
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        binding.recyclerToolbar.title = getString(R.string.change_language)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.mainContainer
            scrimColor = Color.TRANSPARENT
        }
    }


    inner class LocalesAdapter(val locales: List<String>) :
        RecyclerView.Adapter<LocalesAdapter.LocalesViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            LocalesViewHolder(RecyclerItemBinding.inflate(layoutInflater))

        override fun getItemCount() = locales.size

        override fun onBindViewHolder(holder: LocalesViewHolder, position: Int) {
            holder.binding.text.text = locales[position]
            val current = app.prefs.getInt("language", 0)

            if (current == position) {
                holder.binding.text.toggle()
            }
            holder.itemView.setOnClickListener {
                if (current != position) {
                    app.prefs.edit { putInt("language", position) }
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    requireContext().startActivity(intent)
                    requireActivity().finish()
                }
            }
        }

        inner class LocalesViewHolder(val binding: RecyclerItemBinding) :
            RecyclerView.ViewHolder(binding.root)
    }
}