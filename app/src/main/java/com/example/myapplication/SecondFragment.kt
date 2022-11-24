package com.example.myapplication


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.myapplication.databinding.FragmentSecondBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.FileInputStream
import java.net.URI


@AndroidEntryPoint
class SecondFragment : DialogFragment() {

    lateinit var binding: FragmentSecondBinding

    val viewModel: TestViewModel by activityViewModels()



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSecondBinding.inflate(inflater)
        binding.imageViewSizeEditText.setText(viewModel.imageViewSize.toString())
        binding.imageSizeEditText.setText(viewModel.imageSize.toString())
        binding.originalToggleButton.isChecked = viewModel.isOriginal

        this.binding = binding

        binding.container.setOnClickListener {
            hideKeyBoard()
        }

        binding.button.setOnClickListener {
            if (binding.imageSizeEditText.text.toString().isEmpty() || binding.imageTextView.text.toString().isEmpty()) {
                Toast.makeText(requireActivity(), "수치를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.imageViewSize = binding.imageViewSizeEditText.text.toString().toInt()
                viewModel.imageSize = binding.imageSizeEditText.text.toString().toInt()
                viewModel.isOriginal = binding.originalToggleButton.isChecked
                Toast.makeText(requireActivity(), "설정 완료", Toast.LENGTH_SHORT).show()
                dismiss()
            }
        }
        binding.button1.setOnClickListener {
            dismiss()
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun hideKeyBoard() {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        imm.hideSoftInputFromWindow(dialog?.currentFocus?.windowToken,0)
        dialog?.currentFocus?.clearFocus()
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onStop() {
        super.onStop()

    }

    override fun onPause() {
        super.onPause()

    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}