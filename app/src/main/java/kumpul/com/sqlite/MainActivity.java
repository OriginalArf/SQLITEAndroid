package kumpul.com.sqlite;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {

    Button btnAdd,btnGetAll;
    TextView student_Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnGetAll = (Button) findViewById(R.id.btnGetAll);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,StudentDetail.class);
                intent.putExtra("student_Id",0);
                startActivity(intent);
            }
        });

        btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentRepo repo = new StudentRepo(MainActivity.this);

                ArrayList<HashMap<String, String>> studentList =  repo.getStudentList();
                if(studentList.size()!=0) {
                    ListView lv = getListView();
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                            student_Id = (TextView) view.findViewById(R.id.student_Id);
                            String studentId = student_Id.getText().toString();
                            Intent objIndent = new Intent(getApplicationContext(),StudentDetail.class);
                            objIndent.putExtra("student_Id", Integer.parseInt( studentId));
                            startActivity(objIndent);
                        }
                    });
                    ListAdapter adapter = new SimpleAdapter( MainActivity.this,studentList, R.layout.view_student_entry, new String[] { "id","name"}, new int[] {R.id.student_Id, R.id.student_name});
                    setListAdapter(adapter);
                }else{
                    Toast.makeText(MainActivity.this,"No student!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
