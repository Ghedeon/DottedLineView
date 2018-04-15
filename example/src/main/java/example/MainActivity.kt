package example

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ghedeon.dottedlineview.DottedLineView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(dotted_line as DottedLineView) {
            color = Color.BLACK
            radius = 16f
            gap = 16f
        }
    }
}
