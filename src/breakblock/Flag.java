package breakblock;

//一部フラグを管理するクラス
public class Flag {
	
	//initScene2にて、どのボールのボタンが押されたか管理するフラグ(スキル選択なし:0、遅延:1、貫通:2、増殖:3)
	private int skillButtonFlg = 0;
	//1ステージごとのカウント
	private int stageFlg = 0;
	//ボールが発射されたか
	private int ballShotFlg = 0;
	
	Flag(){
		super();
	}
	
	//getter,setter
	public int getSkillButtonFlg() {
		return skillButtonFlg;
	}

	public void setSkillButtonFlg(int skillButtonFlg) {
		this.skillButtonFlg = skillButtonFlg;
	}
	
	public int getStageFlg() {
		return stageFlg;
	}

	public void setStageFlg(int stageFlg) {
		this.stageFlg = stageFlg;
	}

	public int getBallShotFlg() {
		return ballShotFlg;
	}

	public void setBallShotFlg(int ballShotFlg) {
		this.ballShotFlg = ballShotFlg;
	}

}
