package com.example.triplex

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.triplex.databinding.FragmentGameBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment() {
    private var levelDifficulty: Int = 1
    private var maxDifficulty: Int = 3
    private lateinit var gameFactory: GameFactory
    /*  private lateinit var sumProductText: String
      private lateinit var multiplyProductText: String*/

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        val view = binding.root

        setGame()

        binding.codeEditText.setOnEditorActionListener { _, actionId, _ ->
            if (codeEditText.length() == 3) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    val code1 = binding.codeEditText.text?.get(0).toString().toInt()
                    val code2 = binding.codeEditText.text?.get(1).toString().toInt()
                    val code3 = binding.codeEditText.text?.get(2).toString().toInt()
                    hideKeypad()
                    if (gameFactory.codeCheck(code1, code2, code3)) {
                        //Tampilkan Snackbar
                        snackOn(view, "Correct!")
                        levelDifficulty++
                        if (levelDifficulty <= maxDifficulty) {
                            setGame()
                            binding.sumValue.text
                            binding.multiplyValue.text
                            binding.codeEditText.text?.clear()
                        } else {
                            snackOn(
                                view,
                                "Congratulations You Finished the game. Thanks for playing :)"
                            )
                            // Redirect user to congrats page when finished the game
                            Navigation.findNavController(view)
                                .navigate(R.id.action_gameFragment_to_gameFinishFragment)

                        }
                    } else {
                        snackOn(view, "Incorrect!")
                        Navigation.findNavController(view)
                            .navigate(R.id.action_gameFragment_to_gameFinishFragment)
                    }
                    true
                } else {
                    false
                }
            } else {
                Toast.makeText(context, "Please complete the number", Toast.LENGTH_SHORT).show()
                true
            }

        }


        return view
    }

    private fun setGame() {
        gameFactory = GameFactory(levelDifficulty)
        val sumProduct = gameFactory.sumCode()
        val multiplyProduct = gameFactory.multiplyCode()
        gameFactory.logRandom()
        binding.sumValue.text = sumProduct.toString()
        binding.multiplyValue.text = multiplyProduct.toString()
        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.level_text_label) + " (${levelDifficulty}/${maxDifficulty})"
    }

    private fun snackOn(v: View, text: String) {
        val snack = Snackbar.make(v, text, Snackbar.LENGTH_SHORT)
        val view = snack.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.CENTER_VERTICAL
        view.layoutParams = params
        snack.animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE
        snack.show()
    }

    private fun hideKeypad() {
        val inputMethodManager: InputMethodManager =
            activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }


/*
*
*   setGame()
        binding.sumProduct.text = sumProductText
        binding.multiplyProduct.text = multiplyProductText

        binding.enterCodeButton.setOnClickListener {
            if(binding.codeEditText.text.isNotEmpty()){
                var code1 = binding.codeEditText.text[0].toString().toInt()
                var code2 = binding.codeEditText.text[1].toString().toInt()
                var code3 = binding.codeEditText.text[2].toString().toInt()

                if(gameFactory.codeCheck(code1, code2, code3)){
                    Toast.makeText(context, "Bener!", Toast.LENGTH_SHORT).show()
                    levelDifficulty++
                    if (levelDifficulty <= maxDifficulty){
                        setGame()
                        binding.sumProduct.text = sumProductText
                        binding.multiplyProduct.text = multiplyProductText
                        binding.codeEditText.text.clear()
                        binding.level.text = levelDifficulty.toString()
                    }else{
                        Toast.makeText(context, "You Win!", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(context, "Kurang Tepat!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Please input value first", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.codeEditText.setOnEditorActionListener { v, actionId, event ->

            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    if(binding.codeEditText.text.isNotEmpty()){

                        Toast.makeText(context, codeEditText.text, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Please input value first", Toast.LENGTH_SHORT)
                            .show()
                    }
                    true
                }
                else -> false
            }
        }

* */


}