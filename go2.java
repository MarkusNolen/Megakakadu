import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

/**
 * @version 0.1
 * @author Veli Oskari Karttunen
 *
 *T�m� on kaikkein t�rkein osa SlapMAchinea.
 *Ilman moottoreita, SlapMachine ei p��sisi l�psitt�vien luokse.
 *
 */

public class go2 extends Thread {
	
	RegulatedMotor ma = new EV3MediumRegulatedMotor(MotorPort.A);
	RegulatedMotor mb = new EV3MediumRegulatedMotor(MotorPort.B);
	RegulatedMotor mc = new EV3MediumRegulatedMotor(MotorPort.C);
	BumpSensor Bumble = new BumpSensor();
	
	boolean stopReverse = false;
	
	
	/** K��nny vasempaan
	 */
	public void turnLeft(){
		ma.setSpeed(600);
		mb.setSpeed(0);
		mc.setSpeed(400);
		ma.forward();
		mb.backward();
		mc.backward();
	}
	/** K��nny vasempaan, samaan aikaan kuin liikut
	 */
	public void steerLeft(){
		ma.setSpeed(400);
		mb.setSpeed(200);
		mc.setSpeed(400);
		ma.forward();
		mb.backward();
		mc.backward();
	}
	/** K��nny oikealle
	 */
	public void turnRight(){
		ma.setSpeed(400);
		mb.setSpeed(0);
		mc.setSpeed(600);
		ma.backward();
		mb.forward();
		mc.forward();
	}
	/** K��nny oikealle, samaan aikaan kuin liikut
	 */
	public void steerRight(){
		ma.setSpeed(400);
		mb.setSpeed(200);
		mc.setSpeed(400);
		ma.backward();
		mb.forward();
		mc.forward();
	}
	/** Mene suoraan eteenp�in
	 */
	public void moveForward(){
		ma.setSpeed(400);
		mb.setSpeed(400);
		mc.setSpeed(400);
		ma.backward();
		mb.forward();
		mc.backward();
	}
	/** Per��nny
	 */
	public void moveBackward(){
		ma.setSpeed(400);
		mb.setSpeed(400);
		mc.setSpeed(400);
		ma.forward();
		mb.backward();
		mc.forward();
	}
	/** Pys�hdy
	 */
	public void stawp(){
		ma.setSpeed(0);
		mb.setSpeed(0);
		mc.setSpeed(0);
	}
	/** Sulje moottori
	 */
	public void shut(){
		ma.close();
		mb.close();
		mc.close();
	}
	
	public void reverseMenouver(){
		long start = System.currentTimeMillis();
		long end = start + 4000;
		stopReverse = false;
		while (System.currentTimeMillis() < end && stopReverse == false) {
			moveBackward();
			if (Bumble.getSample() == 1) {
				moveForward();
				Delay.msDelay(4000);
				stopRev();
			}
		}
	}
	public void stopRev(){
		stopReverse = true;
	}
}
