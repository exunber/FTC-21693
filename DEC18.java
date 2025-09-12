package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
// import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Blinker;
// import com.qualcomm.robotcore.hardware.IMU; 
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.JavaUtil;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp

public class DEC18 extends LinearOpMode {
    private Blinker control_Hub;
    private Blinker expansion_Hub_2;
    private DcMotor armPos1;
    private DcMotor armPos2;
    private DcMotor bl;
    private DcMotor br;
    private DcMotor fl;
    private DcMotor fr;
    // private IMU imu;
    private CRServo intake;
    private Servo intake_2;
    private DcMotor linearslideL;
    private DcMotor linearslideR;
    private Servo servo_1;
    private Servo servo_2;


    @Override
    public void runOpMode() {
        control_Hub = hardwareMap.get(Blinker.class, "Control Hub");
        expansion_Hub_2 = hardwareMap.get(Blinker.class, "Expansion Hub 2");
        armPos1 = hardwareMap.get(DcMotor.class, "armPos1");
        armPos2 = hardwareMap.get(DcMotor.class, "armPos2");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");
        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        // imu = hardwareMap.get(IMU.class, "imu");
        //intake = hardwareMap.get(CRServo.class, "intake");
        intake_2 = hardwareMap.get(Servo.class, "intake_2");
        linearslideL = hardwareMap.get(DcMotor.class, "linearslideL");
        linearslideR = hardwareMap.get(DcMotor.class, "linearslideR");

        fr.setDirection(DcMotorSimple.Direction.FORWARD);
        br.setDirection(DcMotorSimple.Direction.FORWARD);
        
        fl.setDirection(DcMotorSimple.Direction.FORWARD);
        bl.setDirection(DcMotorSimple.Direction.FORWARD);
      /*  armPos1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armPos2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);*/
      


        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        if (isStopRequested()) return;
        
        while (opModeIsActive()) {
       
            
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            //Printing values
            System.out.print(y);
            double x = gamepad1.left_stick_x * -1.1;
       double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double flPower = (y - x + rx) * .5 / denominator;
            double blPower = (y + x + rx) * .5 / denominator;
            double frPower = (y + x - rx) * .5 / denominator;
            double brPower = (y - x - rx) * .5 / denominator;
          double slidePower = gamepad2.left_trigger - gamepad2.right_trigger;
          double wheelsPower = gamepad1.left_stick_y;
        
            /*fl.setPower(0.5 * wheelsPower);
            bl.setPower(-0.5 * wheelsPower);
            br.setPower(0.5 * wheelsPower);
            fr.setPower(-0.5 * wheelsPower);
*/
        
            fl.setPower(flPower);
            bl.setPower(-blPower);
            fr.setPower(-frPower);
            br.setPower(brPower);
            double armPPower = gamepad2.right_stick_y;
         
         if (gamepad2.dpad_down){
          intake_2.setPosition(.9);
        }
    if (gamepad2.dpad_up){
          intake_2.setPosition(.5);
        }
        
        
        
         armPos1.setPower(armPPower *.25);
         armPos2.setPower(armPPower *.25);
         
          

            
            /* if (gamepad2.right_bumper){
                // moveArm( 0.2, 0);
                 armPos1.setPower(0.2);
            armPos2.setPower(0.2);
            armPos1.setTargetPosition(-90);
            armPos2.setTargetPosition(-90); 
            armPos1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            armPos2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

             }
             
             
             if (gamepad2.left_bumper){
                //moveArm( 0.1, -200);
                 armPos1.setPower(0.1);
            armPos2.setPower(0.1);
            armPos1.setTargetPosition(1250);
            armPos2.setTargetPosition(1350); 
            armPos1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            armPos2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
             }*/
            
            if (slidePower > .5) {
            slidePower = .6;
        }
            
            linearslideR.setPower(slidePower);
            linearslideL.setPower(-slidePower);
          
          if (gamepad2.y){
          linearslideR.setPower(.8);
            linearslideL.setPower(-.8);
            sleep(30000);
            
            
          }
            
            if (linearslideL.getCurrentPosition() < -1500) {
                linearslideL.setPower(0); }
                else
                {linearslideL.setPower(-slidePower);
            }
            
            telemetry.addData("Left Linear slide", linearslideL.getCurrentPosition());
            telemetry.addData("Right Linear slide", linearslideL.getCurrentPosition());
       /* if (gamepad2.dpad_down)
        {
        intake.setPower(.5);
        }
        
        
        else if (gamepad2.dpad_up){
            intake.setPower(-.5);
        } else
        intake.setPower(0);*/
        }
       
            
      // Stop intake when neither bumper is pressed
   
}
}
 /*public void moveArm(double power, int counts) {
         armPos1.setPower(power);
         armPos2.setPower(power);
         armPos1.setTargetPosition(-counts);
         armPos2.setTargetPosition(counts); 
        // sleep(time);
     }
      public void moveArm(double power, int counts) {
         armPos1.setPower(power);
         armPos2.setPower(power);
         armPos1.setTargetPosition(counts);
         armPos2.setTargetPosition(counts); 
        // sleep(time);
     }
}*/



