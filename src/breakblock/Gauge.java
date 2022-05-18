package breakblock;

import java.nio.file.Paths;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Gauge extends ImageView{
	
	Timeline timeline;
	static int time;
	
	static private Image img1 = new Image(Paths.get(Screen.GaugeImg1).toUri().toString());
	static private Image img2 = new Image(Paths.get(Screen.GaugeImg2).toUri().toString());
	static private Image img3 = new Image(Paths.get(Screen.GaugeImg3).toUri().toString());
	static private Image img4 = new Image(Paths.get(Screen.GaugeImg4).toUri().toString());
	static private Image img5 = new Image(Paths.get(Screen.GaugeImg5).toUri().toString());
	
	//コンストラクタ
	Gauge() {
		
		this.setX(Screen.Gauge_X); //X
		this.setY(Screen.Gauge_Y); //Y
	
		//タイムライン(スレッド)
		timeline = new Timeline(new KeyFrame(Duration.millis(1000),event->{run();}));
		timeline.setCycleCount(Timeline.INDEFINITE);//永久に
		timeline.play();//スタート
	}
	
	private void run() {
		
		if(Ball.gaugeFlg >= 1) {
			time += 1;
		}
		System.out.println(time);
		
		if(time >=0&&time<5) {
			setImage(img1);
			System.out.println("gauge1");
		}
		else if(time>=5&&time<10) {
			setImage(img2);
			System.out.println("gauge2");
		}
		else if(time >=10&&time<15) {
			setImage(img3);
			System.out.println("gauge3");
		}
		else if(time >=15&&time<20) {
			setImage(img4);
			System.out.println("gauge4");
		}
		else if(time >=20) {
			setImage(img5);
			System.out.println("gauge5");
		}
	}			
}