import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class main {
	final int N=10;
	public static void main(String[] args) throws Exception {
		String Kword[]={"Julius","Brutus"};//二つのキーワードを文字配列に格納する
		
		//searchメソッドを呼び出し、キーワードが各ファイルにおいてのTFIDFを計算する
		search(Kword[0]);
		search(Kword[1]);


	}
	public static void search(String keyword) throws Exception{
		//ファイルの位置情報を文字配列に格納する
		String file[]={"E:\\search\\Anthony and Cleopatra.txt",
					   "E:\\search\\Macbeth.txt",
				       "E:\\search\\Coriolanus.txt",
				       "E:\\search\\Othello, the Moor of Venice.txt",
				       "E:\\search\\Hamlet, Prince of Denmark.txt",
				       "E:\\search\\Romeo And Juliet.txt",
				       "E:\\search\\Julius Caesar.txt",
				       "E:\\search\\Timon of Athens.txt",
				       "E:\\search\\King Lear.txt",
				       "E:\\search\\Titus Andronicus.txt",	
		};
		
		System.out.println(keyword); //検索対象となるキーワードを表示する。
		System.out.println("IDF="+countIDF(keyword));//検索対象となるキーワードのIDF値を出力する。
		//検索対象となるキーワードの　ファイル名　TF値　tf-idf値を順に出力する
		for (int i = 0; i < file.length; i++) {
			System.out.println(file[i]+":");
			System.out.println("TF="+countTF(file[i], keyword));
			System.out.println("TF-IDF="+countTF(file[i], keyword)*countIDF(keyword));
		}
		
	}
	//TF値を計算するメソッドである。
	public static int countTF(String filename,String keyword)throws Exception{
		ArrayList<String> wordarry=new ArrayList<String>();//単語を格納するためのリストである。
		int count = 0;//TFの初期値を0として設定する。
		try{
			//ファイルを読み込むである
			  File file = new File(filename);
			  BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-16"));
			  //文書を一行ごとに読み込む
			  String line;
			  while((line = br.readLine())!= null ){
				  
				  int index1 = line.indexOf("<");//"<"で始まる行を飛ばす。
				  int length =line.length();
				  if (index1 == -1 && length!=0) {
					  String[] words=line.split("[\\s,.;:!<\\s\\s>()?-]+");//一行の文書を単語ごとにに分割する
					  
					  for (int i = 0; i < words.length; i++) {
						  wordarry.add(words[i]);//単語ごとに配列に格納する
						  
					  }
				  }
			  }
			 // 単語数を数える
			  for (int i = 0; i < wordarry.size(); i++) {
				  if(wordarry.get(i).equals(keyword)){
					  count++;
				  }				
			  }

			  
			  br.close();

			}catch(FileNotFoundException e){
			  System.out.println(e);

			}catch(IOException e){
			  System.out.println(e);
			}

	 return count;//数えた単語数を返す
	}
	//IDF値を計算するメソッドである
	public static float countIDF(String keyword) throws Exception{
		float N=10;//ファイル数を10として宣言する
		int n=0;//キーワードを含むファイル数の初期値として0を宣言する。
		
		String file[]={"E:\\search\\Anthony and Cleopatra.txt",
				   "E:\\search\\Macbeth.txt",
			       "E:\\search\\Coriolanus.txt",
			       "E:\\search\\Othello, the Moor of Venice.txt",
			       "E:\\search\\Hamlet, Prince of Denmark.txt",
			       "E:\\search\\Romeo And Juliet.txt",
			       "E:\\search\\Julius Caesar.txt",
			       "E:\\search\\Timon of Athens.txt",
			       "E:\\search\\King Lear.txt",
			       "E:\\search\\Titus Andronicus.txt",	
	};//10個のファイルの位置情報を文字列に格納する
		
		///countTFメソッドを呼び出し、キーワードを含むファイル数を計算する
		for (int i = 0; i < file.length; i++) {
			if(countTF(file[i], keyword)>0){
				n++;
			}
			
		}
		
		float countIDF=(float) Math.log(N/n);//idfの値を計算する
		return countIDF;//算出したidfの値を返す
	}

}
