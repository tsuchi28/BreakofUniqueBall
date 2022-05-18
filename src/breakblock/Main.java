package breakblock;

import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application{
	
	//変数作成
	Bar bar;//バー
	Ball ball;//ボール
	IncreasedBall ball2; //増殖スキル用ボール
	IncreasedBall ball3; //増殖スキル用ボール
	Block block;//ブロック
	static BlockPane blockpane;//ブロック塊
	Gauge gauge; //スキルゲージ
	static Flag flg; //フラグ管理
	
    //ラベル'ゲームオーバー'①
	static Label lb = new Label("GameOver");
	//ラベル'ライフ'
	Label ll = new Label("Life:"+Ball.life);
	//ラベル'スコア'
	Label sc = new Label("Score:"+BlockPane.score);
	
	//表示のボタン
	static Button startBack = new Button("スタート画面に戻る");
	//
	static Button nextStage = new Button("次のステージへ");
	
	//シーンの宣言
	public static Scene scene1 = null;
	public static Scene scene2 = null;
	public static Scene scene3 = null;
	
	@Override
	public void start(Stage stage) throws Exception{
		
		//Stage設定
		stage.setTitle("Break out of Unique Ball");//タイトル
		//Screenクラス
		stage.setWidth(Screen.Width);	//幅
		stage.setHeight(Screen.Height);	//高さ
		//開くときのウィンドウサイズ
		stage.setWidth(Screen.Width);	//幅
		stage.setHeight(Screen.Height);	//高さ
		//ウィンドウの大きさ固定&全画面無効
	    stage.setResizable(false);
		//ウィンドウを開く位置
		stage.setX(100);	//X
		stage.setY(10);		//Y
		//ステージの作成
		initScene1(stage);
		initScene2(stage);
		initScene3(stage);
		
		//見せるよ
		stage.setScene(scene1);
		stage.show();
		
	}
	
	//スタート画面
	public static void initScene1(Stage stage) {
		AnchorPane AP = new AnchorPane();
		
		//背景画像
		Image img = new Image(Paths.get(Screen.bgImg2).toUri().toString());
		BackgroundImage backimg = new BackgroundImage(img, null, null, null, null);
		Background background = new Background(backimg);
		AP.setBackground(background); 
	    
		//ラベル詳細
		Label lblTitle = new Label("Break out of Unique Ball");
		Font f48 = new Font(48);
		lblTitle.setTextFill(Color.GOLDENROD); 
		lblTitle.setFont(f48);
	    
		//ボタン詳細
		Button btnStart = new Button("スタート");
		btnStart.setPrefSize(300, 50);
		btnStart.setBackground(new Background(new BackgroundFill(Color.DARKGREY,CornerRadii.EMPTY, null)));
		btnStart.setOnAction(event -> setScene(stage,scene2));
		
		//ラベル位置
	    AnchorPane.setTopAnchor(lblTitle, 150.0);
	    AnchorPane.setLeftAnchor(lblTitle, 250.0);
	    AnchorPane.setRightAnchor(lblTitle, 200.0);
	    AnchorPane.setBottomAnchor(lblTitle, 350.0);
	    
	    VBox rootBtn = new VBox();
	    rootBtn.getChildren().addAll(btnStart);
	    
	    //ボタン位置
	    AnchorPane.setTopAnchor(rootBtn, 400.0);
	    AnchorPane.setLeftAnchor(rootBtn, 350.0);
	    AnchorPane.setRightAnchor(rootBtn, 200.0);
	    AnchorPane.setBottomAnchor(rootBtn, 100.0);
	    
	    AP.getChildren().addAll(lblTitle,rootBtn);
	    
	    scene1 = new Scene(AP);
	    
	}

	//ボールセレクト画面
	public static void initScene2(Stage stage) {
		AnchorPane AP = new AnchorPane();
	    Label lbl = new Label("ボール選択");
	    Button Ballbtn1 = new Button("SlowBall");
	    Button Ballbtn2 = new Button("BreakBall");
	    Button Ballbtn3 = new Button("GainBall");
	    Button Select = new Button("NormalBall");
	    Button Backbtn = new Button("戻る");
	    
	    //ラベルの色
	    Font f48 = new Font(48);
	    lbl.setTextFill(Color.BLUE); 
		lbl.setFont(f48);
	    
		//背景画像
	    Image img = new Image(Paths.get(Screen.bgImg).toUri().toString());
		BackgroundImage backimg = new BackgroundImage(img, null, null, null, null);
		Background background = new Background(backimg);
		AP.setBackground(background); 
	    
	    lbl.setFont(Font.font("Cambria", 42));
	    
	    //ボタン配置と色
	    AnchorPane.setTopAnchor(Ballbtn1, 450.0);
	    AnchorPane.setLeftAnchor(Ballbtn1, 100.0);
	    AnchorPane.setRightAnchor(Ballbtn1, 700.0);
	    AnchorPane.setBottomAnchor(Ballbtn1, 150.0);
	    Ballbtn1.setTextFill(Color.WHITE);
	    Ballbtn1.setBackground(new Background(new BackgroundFill(Color.DARKBLUE,CornerRadii.EMPTY, null)));
	    
	    AnchorPane.setTopAnchor(Ballbtn2, 450.0);
	    AnchorPane.setLeftAnchor(Ballbtn2, 400.0);
	    AnchorPane.setRightAnchor(Ballbtn2, 400.0);
	    AnchorPane.setBottomAnchor(Ballbtn2, 150.0);
	    Ballbtn2.setTextFill(Color.WHITE);
	    Ballbtn2.setBackground(new Background(new BackgroundFill(Color.DARKRED,CornerRadii.EMPTY, null)));
	    
	    AnchorPane.setTopAnchor(Ballbtn3, 450.0);
	    AnchorPane.setLeftAnchor(Ballbtn3, 700.0);
	    AnchorPane.setRightAnchor(Ballbtn3, 100.0);
	    AnchorPane.setBottomAnchor(Ballbtn3, 150.0);
	    Ballbtn3.setTextFill(Color.WHITE);
	    Ballbtn3.setBackground(new Background(new BackgroundFill(Color.DARKKHAKI,CornerRadii.EMPTY, null)));
	    
	    AnchorPane.setTopAnchor(Select, 600.0);
	    AnchorPane.setLeftAnchor(Select, 780.0);
	    AnchorPane.setRightAnchor(Select, 50.0);
	    AnchorPane.setBottomAnchor(Select, 30.0);
	    Select.setBackground(new Background(new BackgroundFill(Color.DARKGREY,CornerRadii.EMPTY, null)));
	    
	    AnchorPane.setTopAnchor(Backbtn, 600.0);
	    AnchorPane.setLeftAnchor(Backbtn, 50.0);
	    AnchorPane.setRightAnchor(Backbtn, 780.0);
	    AnchorPane.setBottomAnchor(Backbtn, 30.0);
	    Backbtn.setBackground(new Background(new BackgroundFill(Color.DARKGREY,CornerRadii.EMPTY, null)));
	    
	    //ボール画像配置
	    Image Ball1 = new Image(Paths.get(Screen.BallImg1).toUri().toString());
	    Image Ball2 = new Image(Paths.get(Screen.BallImg2).toUri().toString());
	    Image Ball3 = new Image(Paths.get(Screen.BallImg3).toUri().toString());
	    ImageView imgView1 = new ImageView(Ball1);
	    imgView1.setScaleY(4.0);
	    imgView1.setScaleX(4.0);
	    AnchorPane.setTopAnchor(imgView1, 350.0);
	    AnchorPane.setLeftAnchor(imgView1, 200.0);
	    AnchorPane.setRightAnchor(imgView1, 500.0);
	    AnchorPane.setBottomAnchor(imgView1, 250.0);
	    ImageView imgView2 = new ImageView(Ball2);
	    imgView2.setScaleY(4.0);
	    imgView2.setScaleX(4.0);
	    AnchorPane.setTopAnchor(imgView2, 350.0);
	    AnchorPane.setLeftAnchor(imgView2, 500.0);
	    AnchorPane.setRightAnchor(imgView2, 200.0);
	    AnchorPane.setBottomAnchor(imgView2, 250.0);
	    ImageView imgView3 = new ImageView(Ball3);
	    imgView3.setScaleY(4.0);
	    imgView3.setScaleX(4.0);
	    AnchorPane.setTopAnchor(imgView3, 350.0);
	    AnchorPane.setLeftAnchor(imgView3, 800.0);
	    AnchorPane.setRightAnchor(imgView3, -100.0);
	    AnchorPane.setBottomAnchor(imgView3, 250.0);
	    
	    //ボタンの入力先
	    Select.setOnMouseClicked(event -> setScene(stage,scene3));
	    Backbtn.setOnMouseClicked(event -> setScene(stage,scene1));
	    
	    flg = new Flag();
	    
	    //ボール選択処理(flgを通して渡す)
	    Ballbtn1.setOnMouseClicked(event -> {
	    	flg.setSkillButtonFlg(1);
	    	setScene(stage,scene3);
	    });
	    Ballbtn2.setOnMouseClicked(event -> {
	    	flg.setSkillButtonFlg(2);
	    	setScene(stage,scene3);
	    });
	    Ballbtn3.setOnMouseClicked(event -> {
	    	flg.setSkillButtonFlg(3);
	    	setScene(stage,scene3);
	    });
	    
	    AP.getChildren().addAll(lbl,Ballbtn1,Ballbtn2,Ballbtn3,Select,Backbtn);
	    AP.getChildren().addAll(imgView1,imgView2,imgView3);
	    
	    scene2 = new Scene(AP);
	    
	}
	
	//ゲーム画面
	private void initScene3(Stage stage) throws Exception{
		
		//デバッグ
		Label lb1 = new Label();
		Label lb2 = new Label();
		Label lb3 = new Label();
		
		//変数にイメージ格納
		ball2 = new IncreasedBall();
		ball3 = new IncreasedBall();
		ball = new Ball(flg,ll,lb,startBack,ball2,ball3);//ボールデータ
		bar = new Bar(ball);//バーデータ
		blockpane = new BlockPane(lb/*,b*/,lb1,lb2,lb3,startBack,nextStage,sc);//ブロック塊データ
		gauge = new Gauge(); //スキルデータ
		
		//ball2,3の不可視化
		ball2.setVisible(false);
		ball3.setVisible(false);
		
		//ラベルの設定
		Font f = new Font(50);//フォントを50に設定
		Font f2 = new Font(80);
		lb.setFont(f2);//フォントを適用
		lb.setVisible(false);//falseの時見えなくする
		lb.setTextFill(Color.WHITE);//文字色を赤
		lb.setLayoutX(300);//x座標が380
		lb.setLayoutY(280);//ｙ座標200
		
		ll.setFont(f); //フォントを適用
		ll.setTextFill(Color.RED);//文字色を赤
		ll.setLayoutX(Screen.Life_X);
		ll.setLayoutY(Screen.Life_Y);
		
		sc.setFont(f); //フォントを適用
		sc.setTextFill(Color.RED);//文字色を赤
		sc.setLayoutX(Screen.Score_X);
		sc.setLayoutY(Screen.Score_Y);
		
		//ボタン作成	
		startBack.setPrefSize(200, 50);
		startBack.setVisible(false);//falseの時見えなくする
		startBack.setLayoutX(275);//x座標400
		startBack.setLayoutY(400);//y座標400
		startBack.setOnAction(event -> reset());
		startBack.setOnMouseClicked(event -> setScene(stage,scene1) );
		startBack.setBackground(new Background(new BackgroundFill(Color.DARKGREY,CornerRadii.EMPTY, null)));
		
		//
		nextStage.setPrefSize(200, 50);
		nextStage.setVisible(false);//falseの時見えなくする
		nextStage.setLayoutX(525);//x座標400
		nextStage.setLayoutY(400);//y座標400
		nextStage.setOnAction(event -> next());
		nextStage.setOnMouseClicked(event -> setScene(stage,scene3) );
		nextStage.setBackground(new Background(new BackgroundFill(Color.DARKGREY,CornerRadii.EMPTY, null)));
		
		//デバッグラベルの設定
		lb1.setLayoutX(0);
		lb1.setLayoutY(100);
		lb2.setLayoutX(0);
		lb2.setLayoutY(200);
		lb3.setLayoutX(0);
		lb3.setLayoutY(300);
		lb1.setTextFill(Color.CYAN);
		lb2.setTextFill(Color.CYAN);
		lb3.setTextFill(Color.CYAN);
		lb1.setFont(f);
		lb2.setFont(f);
		lb3.setFont(f);
				
		//ボタン作成
		//Button b = new Button("reset");//'reset'表示のボタン
		//b.setVisible(false);//falseの時見えなくする
		//b.setTextFill(Color.RED);//文字色赤
		//b.setLayoutX(380);//x座標380
		//b.setLayoutY(180);//y座標180
				
		//オブザーバー登録
		ball.addObserver(blockpane);
		ball.addObserver(bar);
		ball2.addObserver(blockpane);
		ball2.addObserver(bar);
		ball3.addObserver(blockpane);
		ball3.addObserver(bar);
						
		//配置
		Pane root = new Pane();//下地ペイン
				
		//背景色
		Image img = new Image(Paths.get(Screen.bgImg).toUri().toString());
		BackgroundImage backimg = new BackgroundImage(img, null, null, null, null);
		Background background = new Background(backimg);
		root.setBackground(background); 
				
		//rootに部品を乗せる
		root.getChildren().addAll(bar,blockpane,gauge,ball,ball2,ball3,lb,ll,sc/*,b:*/,lb1,lb2,startBack,nextStage);
				
		//rootを載せたScene生成
		scene3 = new Scene(root);
				
		//キー入力登録
		scene3.setOnKeyPressed(event -> onKeyPressed(event));//onkeypressedの内容を登録
		//stage.setScene(scene3);
		//b.setOnAction(event -> buttonPressed());
	}

	public void onKeyPressed(KeyEvent event){
		//バー移動
		if(event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT) { //左右キー
			bar.move(event.getCode());//バーmove(バークラス参照)
		}
		//球発射
		if(event.getCode() == KeyCode.UP) { //upキー
			ball.move(bar.getX());//球クラス参照
		}
		//スキル発動
		if(event.getCode() == KeyCode.DOWN) { //downキー
			ball.skillActive(bar);//球クラス参照
		}
	}
	
	public static void reset() {
		blockpane.reset();
		lb.setVisible(false);
		startBack.setVisible(false);
		nextStage.setVisible(false);
	}
	
	public static void next() {
		flg.setStageFlg(flg.getStageFlg()+1);
		blockpane.next(flg.getStageFlg());
		lb.setVisible(false);
		startBack.setVisible(false);
		nextStage.setVisible(false);
	}
	
	public static void main(String[] args) {
		launch();
	}
	
//	void buttonPressed() {
//		reset();
//	}
	
    public static void setScene(Stage stage,Scene changeScene) {
	    stage.setScene(changeScene);
	    stage.show();
    }
    
	
//	void reset() {
//		blockpane.reset();
//		bar.reset();
//		score.reset();
//		invader.reset();
//	}
    
}