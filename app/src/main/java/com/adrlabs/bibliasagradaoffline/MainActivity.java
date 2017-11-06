package com.adrlabs.bibliasagradaoffline;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    TextView tv1;
    //ScrollView sv1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=(TextView)findViewById(R.id.textView1);
        //sv1=(ScrollView) findViewById(R.id.scrollViewId);
        try {
            InputStream is = getAssets().open("nvi_gn.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            Element element=doc.getDocumentElement();
            element.normalize();

            NodeList nList = doc.getElementsByTagName("chapter");
            for (int i=0; i<nList.getLength(); i++) {
                //Toast.makeText(getApplicationContext(), "nList.getLenght: "+ nList.getLength(), Toast.LENGTH_SHORT).show();

                Node node = nList.item(i);

/*                switch(node.getNodeType()) {
                    case Node.CDATA_SECTION_NODE:
                        Log.d("section_node", "section_node: " + Node.CDATA_SECTION_NODE);
                        break;
                    case Node.COMMENT_NODE:
                        Log.d("COMMENT_NODE", "section_node: " + Node.COMMENT_NODE);
                        break;
                    case Node.ENTITY_NODE:
                        Log.d("ENTITY_NODE", "ENTITY_NODE: " + Node.ENTITY_NODE);
                        break;
                    case Node.TEXT_NODE:
                        Log.d("TEXT_NODE", "TEXT_NODE: " + Node.TEXT_NODE);
                        break;
                    case Node.ELEMENT_NODE:
                        Log.d("ELEMENT_NODE", "ELEMENT_NODE: " + Node.ELEMENT_NODE);
                        break;
                }
*/
                 if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node;
                    tv1.setText(tv1.getText()+"\nVerso : " + getValue("verse", element2)+"\n");
                    //tv1.setText(tv1.getText()+"\nVerso : " + getValue("verse", element2)+"\n");
                    Log.d("element2", "element2: " + element2);
                     tv1.setMovementMethod(new ScrollingMovementMethod());
                    // sv1.addView(tv1);
                }
            }//end of for loop

        } catch (Exception e) {e.printStackTrace();}
    }
    private static String getValue(String tag, Element element) {
        //NodeList nodeList = element.getAttributeNode();
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

}