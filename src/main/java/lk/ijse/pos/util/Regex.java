package lk.ijse.pos.util;

import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Regex {


    private static boolean isTextFieldValid(TextFeid textField, String text) {
        String field ="";

        switch (textField) {
            case Email :
                field = "^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";
                break;
            case TelNo :
                field = "^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$";
                break;
            case UID:
                field = "^U001$";
                break;
            case Uid:
                field = "^U001$";
                break;
            case QTY:
                field =  "^\\d+$";
                break;
            case Price:
                field = "^([0-9]){1,}[.]([0-9]){1,}$";
                break;
            case Ename:
                field = "^[A-z|\\s]{3,}$";
                break;
            case Address:
                field =  "^([A-z0-9]|[-/,.@+]|\\s){4,}$";
                break;
            case jobRole:
                field ="^[A-Za-z0-9\s-]+$";
                break;
            case Cname:
                field = "^[A-z|\\s]{3,}$";
                break;
            case SName:
                field = "^[A-z|\\s]{3,}$";
                break;
            case qty:
                field = "^\\d+$";
                break;
            case issuedFlowers:
                field = "^\\d+$";
                break;
            case PName:
                field ="^[A-z|\\s]{3,}$";
                break;
            case price:
                field ="^([0-9]){1,}[.]([0-9]){1,}$";
                break;
            case EName:
                field ="^[A-z|\\s]{3,}$";
                break;
            case paidPayment:
                field ="^([0-9]){1,}[.]([0-9]){1,}$";
                break;
            case Balance:
                field ="^([0-9]){1,}[.]([0-9]){1,}$";
                break;
            case Amount:
                field = "^([0-9]){1,}[.]([0-9]){1,}$";
                break;
            case FName:
                field ="^[A-z|\\s]{3,}$";
                break;
            case StockId:
                field ="^[A-z|\\s]{3,}$";
                break;
            case QtyOnHand:
                field ="^\\d+$";
                break;

        }

        Pattern pattern = Pattern.compile(field);

        if (text != null){
            if (text.trim().isEmpty()){
                return false;
            }
        } else {
            return false;
        }

        Matcher matcher = pattern.matcher(text);

        if (matcher.matches()){
            return true;
        }
        return false;
    }
    public static boolean setTextColor(TextFeid location, TextField field){
        if (isTextFieldValid(location, field.getText())) {
            field.setStyle("-fx-border-color: blue");
            return true;
        } else {
            field.setStyle("-fx-border-color: red");
            return false;
        }
    }

    }
   /* public static boolean setTextColor(TextField field) {
        if (isTextFieldValid(field, field.getText())) {
            field.setStyle("-fx-border-color: blue");
            return true;
        } else {
            field.setStyle("-fx-border-color: red");
            return false;
        }
    }*/




