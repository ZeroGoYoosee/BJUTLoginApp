package xin.z7workbench.bjutloginapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.MaterialSharedAxis
import xin.z7workbench.bjutloginapp.R
import xin.z7workbench.bjutloginapp.databinding.RecyclerBinding
import xin.z7workbench.bjutloginapp.databinding.RecyclerItemBinding
import java.text.FieldPosition

class LocaleFragment : BasicFragment<RecyclerBinding>() {
    private val values by lazy {
        resources.getStringArray(R.array.language_values)
    }
    val setting: String
        get() = app.prefs.getString("language", values.first())!!

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
            RecyclerBinding.inflate(inflater, container, false)

    override fun initViewAfterViewCreated() {
        binding.recyclerToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        binding.recycler.run {
            adapter = LocalesAdapter(resources.getStringArray(R.array.language).toList())
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        binding.recyclerToolbar.title = getString(R.string.settings_language_title)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
    }

    inner class LocalesAdapter(val locales: List<String>) : RecyclerView.Adapter<LocalesAdapter.LocalesViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                LocalesViewHolder(RecyclerItemBinding.inflate(layoutInflater))

        override fun getItemCount() = locales.size

        override fun onBindViewHolder(holder: LocalesViewHolder, position: Int) {
            holder.binding.text.text = locales[position]
            holder.itemView.setOnClickListener {

            }
        }

        inner class LocalesViewHolder(val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root)
    }
}