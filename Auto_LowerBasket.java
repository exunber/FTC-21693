package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class Auto_LowerBasket extends LinearOpMode {
    private Blinker control_Hub;
    private Blinker expansion_Hub_2;
    private DcMotor armPos1;
    private DcMotor armPos2;
    private DcMotor bl;
    private DcMotor br;
    private DcMotor fl;
    private DcMotor fr;
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
        intake_2 = hardwareMap.get(Servo.class, "intake_2");
        linearslideL = hardwareMap.get(DcMotor.class, "linearslideL");
        linearslideR = hardwareMap.get(DcMotor.class, "linearslideR");
        
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        
        waitForStart();

        // Example usage of the functions
         closeIntake();
        moveFB(0.4, 900);
       // moveSlide(0.2, )
    }

    public void openIntake() {
        intake_2.setPosition(.2);
        sleep(800);
    }

    public void closeIntake() {
        intake_2.setPosition(1);
        sleep(800);
    }

    public void moveSlide(double power, int time) {
        linearslideR.setPower(-power);
        linearslideL.setPower(power);
        sleep(time);
        linearslideR.setPower(0);
        linearslideL.setPower(0);
    }

    public void moveRL(double power, int time) {
        fr.setPower(power);
        br.setPower(power);
        fl.setPower(power);
        bl.setPower(power);
        sleep(time);
        setPowerAllWheels(0);
    }

    public void moveFB(double power, int time) {

        fr.setPower(-power);
        br.setPower(power);
        fl.setPower(power);
        bl.setPower(-power);
        sleep(time);
        setPowerAllWheels(0);
    }

    public void turnRL(double power, int time) {
        fr.setPower(-power);
        br.setPower(power);
        fl.setPower(-power);
        bl.setPower(power);
        sleep(time);
        setPowerAllWheels(0);
        
    }

    public void setPowerAllWheels(double power) {
        fr.setPower(power);
        br.setPower(power);
        fl.setPower(power);
        bl.setPower(power);
    }

    public void allWheelRunToPosition() {
        fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void moveArm(double power, int time) {
        armPos1.setPower(power);
        armPos2.setPower(power);
        sleep(time);
        armPos1.setPower(0);
        armPos2.setPower(0);
    }

    public void resetEncoders() {
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
