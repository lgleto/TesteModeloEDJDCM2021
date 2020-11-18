package ipca.temperaturas.a000000

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
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

    // resposta 7
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_media -> {
                Toast.makeText(this, "Temperatura media:${media()}", Toast.LENGTH_LONG).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun media() : Double{
        if (tempRegistList.size < 1) return 0.0
        var sum = 0.0
        tempRegistList.forEach {
            sum += it.temperature?:0.0
        }
        return sum/tempRegistList.size
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
            val buttonRemove = rowView.findViewById<Button>(R.id.buttonRemove)

            textViewLocalName.text = tempRegistList[position].localName
            textViewTemperature.text = String.format("%.02f",tempRegistList[position].temperature)

            // resposta 5
            rowView.isClickable = true
            rowView.setOnClickListener {
                val intent = Intent (this@MainActivity, TempDetailActivity::class.java)
                intent.putExtra(TempDetailActivity.LOCAL_NAME   , tempRegistList[position].localName  )
                intent.putExtra(TempDetailActivity.DATE         , tempRegistList[position].date?.time )
                intent.putExtra(TempDetailActivity.TEMPERATURE  , tempRegistList[position].temperature)
                startActivity(intent)
            }

            //resposta 6
            buttonRemove.setOnClickListener {
                tempRegistList.removeAt(position)
                adapter.notifyDataSetChanged()
            }

            return rowView
        }

    }
}