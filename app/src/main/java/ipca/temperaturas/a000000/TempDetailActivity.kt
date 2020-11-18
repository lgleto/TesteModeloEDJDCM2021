package ipca.temperaturas.a000000

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.*

// resposta 4
class TempDetailActivity : AppCompatActivity() {


    companion object {
        const val LOCAL_NAME   = "local_name"
        const val DATE         = "date"
        const val TEMPERATURE  = "temperature"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp_detail)


        val localName = intent.getStringExtra(LOCAL_NAME)
        val dateNum = intent.getLongExtra(DATE,0)
        val temperature = intent.getDoubleExtra(TEMPERATURE,0.0)

        val textViewDetailLocalName = findViewById<TextView>(R.id.textViewDetailLocalName)
        val textViewDetailDate = findViewById<TextView>(R.id.textViewDetailDate)
        val textViewDetailTemperature = findViewById<TextView>(R.id.textViewDetailTemperature)

        textViewDetailLocalName.text = localName
        textViewDetailDate.text = Date(dateNum).toString()
        textViewDetailTemperature.text = temperature.toString()

    }
}