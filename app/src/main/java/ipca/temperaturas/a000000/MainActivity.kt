package ipca.temperaturas.a000000

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    //data model

    // Reposta 3
    var tempRegistList : MutableList<TempRegist> = ArrayList()
    lateinit var listViewTemp : ListView
    lateinit var adapter: TempAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tempRegistList.add(TempRegist("Braga", Date(), 17.4))
        tempRegistList.add(TempRegist("Barcelos", Date(), 18.4))
        tempRegistList.add(TempRegist("Famalicão", Date(), 16.4))
        tempRegistList.add(TempRegist("Guimamrães", Date(), 14.4))


        // Reposta 3
        listViewTemp = findViewById(R.id.listViewTemp)
        adapter = TempAdapter()
        listViewTemp.adapter = adapter



    }

    // Reposta 3
    inner class TempAdapter : BaseAdapter(){
        override fun getCount(): Int {
           return tempRegistList.size
        }

        override fun getItem(position: Int): Any {
            return  tempRegistList[position]
        }

        override fun getItemId(p0: Int): Long {
            return 0
        }

        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.row_temp, viewGroup, false)
            val textViewLocalName = rowView.findViewById<TextView>(R.id.textViewLocalName)
            val textViewTemperature = rowView.findViewById<TextView>(R.id.textViewTemperature)

            textViewLocalName.text = tempRegistList[position].localName
            textViewTemperature.text = String.format("%.02f",tempRegistList[position].temperature)

            rowView.isClickable = true
            rowView.setOnClickListener {
                val intent = Intent (this@MainActivity, TempDetailActivity::class.java)
                intent.putExtra(TempDetailActivity.LOCAL_NAME   , tempRegistList[position].localName  )
                intent.putExtra(TempDetailActivity.DATE         , tempRegistList[position].date?.time )
                intent.putExtra(TempDetailActivity.TEMPERATURE  , tempRegistList[position].temperature)
                startActivity(intent)
            }

            return rowView
        }

    }
}