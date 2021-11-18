package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botaoCalcular.setOnClickListener { calculoGorjeta() }

    }

    fun calculoGorjeta() {
        val campoTexto = binding.custoDoServico.text.toString()
        val custo = campoTexto.toDoubleOrNull()
        val escolhaId = binding.opcaoGorjeta.checkedRadioButtonId
        val porcentGorjeta = when (escolhaId) {
            R.id.vinte_porcento -> 0.20
            R.id.dezoito_porcento -> 0.18
            else -> 0.15
        }
        if (custo == null){
            binding.resultadoGorjeta.text = ""
            return
        }
        var gorjeta = porcentGorjeta * custo
        val arredondar = binding.arredondarSwitch.isChecked
        if (arredondar) {
            gorjeta = kotlin.math.ceil(gorjeta)
        }
        val gorjetaFormatada = NumberFormat.getCurrencyInstance().format(gorjeta)
        binding.resultadoGorjeta.text = getString(R.string.valor_da_gorjeta, gorjetaFormatada)
    }

}