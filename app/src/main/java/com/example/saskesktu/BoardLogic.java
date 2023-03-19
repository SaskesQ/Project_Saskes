package com.example.saskesktu;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

import java.util.Arrays;

import kotlin.collections.IntIterator;

public class BoardLogic {
    String[] statusArray; //Visų šaškių statusai
    int[] checkerIDs; //Visų šaškių ID. Naudojamas kartu su statusArray
    boolean pressedStatus; //1 - Šaškė paspausta; 0 - Šaškė nepaspausta
    int pressedId; //Paspaustos šaškės id.

    int WhiteCaptured = 0; //Nukirstos baltos šaškės.

    int BlackCaptured = 0; //Nukirstos juodos šaškės.

    int move_count = 0;
    public BoardLogic(){
        statusArray = new String[]{
                "Black", "NA", "Black", "NA", "Black", "NA", "Black", "NA",
                "NA", "Black", "NA", "Black", "NA", "Black", "NA", "Black",
                "Black", "NA", "Black", "NA", "Black", "NA", "Black", "NA",
                "NA", "0", "NA", "0", "NA", "0", "NA", "0",
                "0", "NA", "0", "NA", "0", "NA", "0", "NA",
                "NA", "Light", "NA", "Light", "NA", "Light", "NA", "Light",
                "Light", "NA", "Light", "NA", "Light", "NA", "Light", "NA",
                "NA", "Light", "NA", "Light", "NA", "Light", "NA", "Light"
        };

        checkerIDs = new int[] {R.id.c0, R.id.c1, R.id.c2, R.id.c3, R.id.c4, R.id.c5, R.id.c6, R.id.c7,
                                    R.id.c8, R.id.c9, R.id.c10, R.id.c11, R.id.c12, R.id.c13, R.id.c14, R.id.c15,
                                    R.id.c16, R.id.c17, R.id.c18, R.id.c19, R.id.c20, R.id.c21, R.id.c22, R.id.c23,
                                    R.id.c24, R.id.c25, R.id.c26, R.id.c27, R.id.c28, R.id.c29, R.id.c30, R.id.c31,
                                    R.id.c32, R.id.c33, R.id.c34, R.id.c35, R.id.c36, R.id.c37, R.id.c38, R.id.c39,
                                    R.id.c40, R.id.c41, R.id.c42, R.id.c43, R.id.c44, R.id.c45, R.id.c46, R.id.c47,
                                    R.id.c48, R.id.c49, R.id.c50, R.id.c51, R.id.c52, R.id.c53, R.id.c54, R.id.c55,
                                    R.id.c56, R.id.c57, R.id.c58, R.id.c59, R.id.c60, R.id.c61, R.id.c62, R.id.c63};


        pressedStatus = false;
        pressedId = -1;
    }

    /**
     * Patikrina ar langelis yra tuščias
     * ** Pakoreguota dėl karalienės judėjimo
     */

    /**
     * Tikrinima ar yra galimų ėjimų
     * Gražina laimėtojų pavadinimą, kuris reiškia laimėtoją arba dar nieką nelaimėjusį
     * Light - Raudoni laimėjo; Black - Juodi laimėjo; None - niekas nelaimėjo(Mačas dar vyksta);
     */
    String EveryCheckerPossibleMoves(int whichMethod){

        int[] possibleMovesWhite = null;
        int[] possibleKirtimaiWhite = null;
        int[] possibleMovesBlack = null;
        int[] possibleKirtimaiBlack = null;
        boolean Black_status = true;
        boolean Light_status = true;
        if(whichMethod==1) {
            for (int i = 0; i < 64; i++)
                if (statusArray[i] == "Light" || statusArray[i] == "QLight") {
                    possibleMovesWhite = FindAllPossibleMoves(i, statusArray[i]);

                    for (int j = 0; j < 40; j++)
                        if (possibleMovesWhite[j] != -1) {
                            Light_status = false;
                            break;
                        }
                    if (Light_status == false)
                        break;
                    possibleKirtimaiWhite = FindAllPossibleKirtimai(i, statusArray[i]);
                    for (int j = 0; j < 40; j++)
                        if (possibleKirtimaiWhite[j] != -1) {
                            Light_status = false;
                            break;
                        }
                    if (Light_status == false)
                        break;
                } else if (statusArray[i] == "Black" || statusArray[i] == "QBlack") {
                    possibleMovesBlack = FindAllPossibleMoves(i, statusArray[i]);
                    for (int j = 0; j < 40; j++)
                        if (possibleMovesBlack[j] != -1) {
                            Black_status = false;
                            break;
                        }
                    if (Black_status == false)
                        break;

                    possibleKirtimaiBlack = FindAllPossibleKirtimai(i, statusArray[i]);
                    for (int j = 0; j < 40; j++)
                        if (possibleKirtimaiBlack[j] != -1) {
                            Black_status = false;
                            break;
                        }
                    if (Black_status == false)
                        break;

                }
        }


        else{
            for (int i = 0; i < 64; i++)
                if (statusArray[i] == "Light" || statusArray[i] == "QLight") {
                    possibleMovesWhite = FindAllPossibleMoves2(i, statusArray[i]);

                    for (int j = 0; j < 40; j++)
                        if (possibleMovesWhite[j] != -1) {
                            Light_status = false;
                            break;
                        }
                    if (Light_status == false)
                        break;
                    possibleKirtimaiWhite = FindAllPossibleKirtimai2(i, statusArray[i]);
                    for (int j = 0; j < 40; j++)
                        if (possibleKirtimaiWhite[j] != -1) {
                            Light_status = false;
                            break;
                        }
                    if (Light_status == false)
                        break;
                } else if (statusArray[i] == "Black" || statusArray[i] == "QBlack") {
                    possibleMovesBlack = FindAllPossibleMoves2(i, statusArray[i]);

                    for (int j = 0; j < 40; j++)
                        if (possibleMovesBlack[j] != -1) {
                            Light_status = false;
                            break;
                        }
                    if (Light_status == false)
                        break;
                    possibleKirtimaiBlack = FindAllPossibleMoves2(i, statusArray[i]);
                    for (int j = 0; j < 40; j++)
                        if (possibleKirtimaiBlack[j] != -1) {
                            Black_status = false;
                            break;
                        }
                    if (Black_status == false)
                        break;

                }

        }

        if (Light_status == true)
            return "Black";
        else if (Black_status == true)
            return "Light";
        else
            return "None";

    }

    /**
     * Keičia puses
     */
    public void SwitchWasPressed(Context C, View Layout) {
        if (move_count == 0) {


            String[] colors = {"Black", "Light", "QBlack", "QLight", "BlackPressed", "LightPressed"};

            for (int i = 0; i < 64; i++)
                switch (statusArray[i]) {

                    case "Black":
                        statusArray[i] = "Light";

                        View checkerV1 = Layout.findViewById(checkerIDs[i]);

                        checkerV1.setBackground(ContextCompat.getDrawable(C, R.drawable.light_piece));

                        // checkerView.setBackground(ContextCompat.getDrawable(C, R.drawable.light_piece));
                        break;

                    case "Light":
                        statusArray[i] = "Black";

                        View checkerV2 = Layout.findViewById(checkerIDs[i]);

                        checkerV2.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_piece));

                        // checkerView.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_piece));
                        break;
                    case "QBlack":
                        statusArray[i] = "QLight";
                        // checkerView.setBackground(ContextCompat.getDrawable(C, R.drawable.light_king_piece));
                        View checkerV3 = Layout.findViewById(checkerIDs[i]);

                        checkerV3.setBackground(ContextCompat.getDrawable(C, R.drawable.light_king_piece));

                        break;
                    case "QLight":
                        statusArray[i] = "QBlack";
                        View checkerV4 = Layout.findViewById(checkerIDs[i]);

                        checkerV4.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_king_piece));
                        // checkerView.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_king_piece));
                        break;
                    case "BlackPressed":
                        statusArray[i] = "LightPressed";

                        View checkerV5 = Layout.findViewById(checkerIDs[i]);

                        checkerV5.setBackground(ContextCompat.getDrawable(C, R.drawable.light_piece_pressed));
                        // checkerView.setBackground(ContextCompat.getDrawable(C, R.drawable.light_piece_pressed));
                        break;
                    case "LightPressed":
                        statusArray[i] = "BlackPressed";
                        View checkerV6 = Layout.findViewById(checkerIDs[i]);

                        checkerV6.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_piece_pressed));
                        // checkerView.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_piece_pressed));
                        break;


                }
        } else {





        }


        DebugStatusArray();


    }




    boolean IsPlaceFree(int placeId, String color)
    {
        String[] colors = {"Black", "Light", "QBlack", "QLight"};
        for(int i = 0; i < 4; i++){
            if(statusArray[placeId] == colors[i])
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Patikrina, ar langelis yra priešo šaškė
     */
    boolean IsEnemyChecker(int placeId, String color)
    {
        if(color == "Black" || color == "QBlack")
        {
            if(statusArray[placeId] == "Light" || statusArray[placeId] == "QLight")
            {
                return true;
            }
        }else if (color == "Light" || color == "QLight")
        {
            if(statusArray[placeId] == "Black" || statusArray[placeId] == "QBlack")
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Suranda visus galimus kirtimus
     * ** Pakoreguota funkcija. Suranda ir karalienes galimus kirtimus
     */
    int[] FindAllPossibleKirtimai(int placeId, String color) {
        int[] possibleMoves = new int[40];
        Arrays.fill(possibleMoves, -1);

        int checkerRow = (placeId) / 8;

        possibleMoves[0] = placeId - 9;
        possibleMoves[1] = placeId - 7;
        possibleMoves[2] = placeId + 7;
        possibleMoves[3] = placeId + 9;

        for (int i = 0; i < 40; i++) {
            if(possibleMoves[i] != -1)
            {
                if((possibleMoves[i]) / 8 != checkerRow + 1){
                    if((possibleMoves[i]) / 8 != checkerRow - 1) {
                            possibleMoves[i] = -1;
                    }
                }
            }
        }

        for(int i = 0; i < 40; i++)
        {
            Log.d("t", Integer.toString(possibleMoves[i]));
            if(color == "Black" || color == "QBlack")
            {
                if(statusArray[possibleMoves[i]] != "Light")
                {
                    possibleMoves[i] = -1;
                }
            }else if(color == "Light" || color == "QLight")
            {
                if(statusArray[possibleMoves[i]] != "Black")
                {
                    possibleMoves[i] = -1;
                }
            }
        }

        for(int i = 0; i < 40; i++) {
            if (possibleMoves[i] + (possibleMoves[i] - placeId) < 0 || possibleMoves[i] + (possibleMoves[i] - placeId) > 63) {
                possibleMoves[i] = -1;
            }
        }

        for(int i = 0; i < 40; i++)
        {
            if(possibleMoves[i] != -1)
            {

                if(statusArray[possibleMoves[i] + (possibleMoves[i] - placeId)] != "0")
                {
                    possibleMoves[i] = -1;
                }

            }


        }



        return possibleMoves;

    }

    /**
     * Suranda visus galimus judėti langelius
     * ** Pakoreguota funkcija, kad rodytu ir karalienės galimus ėjimus
     */
    int[] FindAllPossibleMoves(int placeId, String color)
    {
        int[] possibleMoves = new int[40];
        Arrays.fill(possibleMoves, -1);

        int checkerRow = (placeId) / 8;


        if(color == "Black")
        {
            possibleMoves[0] = placeId + 9;
            possibleMoves[1] = placeId + 7;
        }else if(color == "Light")
        {
            possibleMoves[0] = placeId - 9;
            possibleMoves[1] = placeId - 7;
        }else if(color == "QBlack"){
            possibleMoves[0] = placeId + 9;
            possibleMoves[1] = placeId + 7;
            possibleMoves[2] = placeId - 9;
            possibleMoves[3] = placeId - 7;
        }else if(color == "QLight"){
            possibleMoves[0] = placeId - 9;
            possibleMoves[1] = placeId - 7;
            possibleMoves[2] = placeId + 9;
            possibleMoves[3] = placeId + 7;
        }

        for(int i = 0; possibleMoves[i] != -1; i++)
        {
            if(possibleMoves[i] > 63)
            {
                possibleMoves[i] = -1;
            }else if(possibleMoves[i] < 0)
            {
                possibleMoves[i] = -1;
            }

            if(possibleMoves[i] != -1)
            {
                if(color == "Black")
                {
                    if((possibleMoves[i]) / 8 != checkerRow + 1)
                    {
                        possibleMoves[i] = -1;
                    }
                }else if(color == "Light")
                {
                    if((possibleMoves[i]) / 8 != checkerRow - 1)
                    {
                        possibleMoves[i] = -1;
                    }
                }
            }
        }
        return possibleMoves;
    }

    /**
     * Pažymi visas galimas judėti vietas
     */
    public void HighlightPossibleMoves(View V, Context C, int placeId, String color)
    {
        int[] possibleMoves = FindAllPossibleMoves(placeId, color);

        for(int i = 0; i < 40; i++)
        {
            if( possibleMoves[i] != -1)
            {

                if(IsPlaceFree(possibleMoves[i], color))
                {
                    if(IsEnemyChecker(possibleMoves[i], color))
                    {

                    }else{
                        View testV = V.findViewById(checkerIDs[possibleMoves[i]]);

                        testV.setBackground(ContextCompat.getDrawable(C, R.drawable.available_move));

                        statusArray[possibleMoves[i]] = "AvailableMove";
                    }
                }
            }
        }
    }

    /**
     * Panaikina pažymėtas galimas vietas
     */
    public void removeHighlightedMoves(View V, Context C)
    {
        for(int i = 0; i < 64; i++)
        {
            if(statusArray[i] == "AvailableMove"){
                statusArray[i] = "0";

                View checkerV = V.findViewById(checkerIDs[i]);

                checkerV.setBackground(ContextCompat.getDrawable(C, R.drawable.blank_square));
            }
        }
    }

    /**
     * Šaškės judėjimo funkcija.
     * ** Pakoreguota funkcija, ėjimo metu tikrina ar figūrėlė gali tapti karaliene, jei taip
     * ** figurėlė patampa karaliene(QLight/QBlack).
     * ** Pridėtas karalienės judėjimas lentoje
     */
    public void MoveCheckerPiece(View V, Context C, int checkerId, int whereToId)
    {

        if(statusArray[checkerId] == "BlackPressed"){

            View destination = V.findViewById(checkerIDs[whereToId]);
            View source = V.findViewById(checkerIDs[checkerId]);

            boolean queen = CanBeQueen(whereToId, "Black");
            if(queen){
                destination.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_king_piece));
                source.setBackground(ContextCompat.getDrawable(C, R.drawable.blank_square));
                statusArray[whereToId] = "QBlack";
            }else{
                destination.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_piece));
                source.setBackground(ContextCompat.getDrawable(C, R.drawable.blank_square));
                statusArray[whereToId] = "Black";
            }
        }else if(statusArray[checkerId] == "LightPressed")
        {
            View destination = V.findViewById(checkerIDs[whereToId]);
            View source = V.findViewById(checkerIDs[checkerId]);
            boolean queen = CanBeQueen(whereToId, "Light");
            if(queen){
                destination.setBackground(ContextCompat.getDrawable(C, R.drawable.light_king_piece));
                source.setBackground(ContextCompat.getDrawable(C, R.drawable.blank_square));
                statusArray[whereToId] = "QLight";
            }
            else{
                destination.setBackground(ContextCompat.getDrawable(C, R.drawable.light_piece));
                source.setBackground(ContextCompat.getDrawable(C, R.drawable.blank_square));
                statusArray[whereToId] = "Light";
            }
        }else if(statusArray[checkerId] == "QBlackPressed"){
            View destination = V.findViewById(checkerIDs[whereToId]);
            View source = V.findViewById(checkerIDs[checkerId]);

            destination.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_king_piece));
            source.setBackground(ContextCompat.getDrawable(C, R.drawable.blank_square));
            statusArray[whereToId] = "QBlack";
        }else if(statusArray[checkerId] == "QLightPressed"){
            View destination = V.findViewById(checkerIDs[whereToId]);
            View source = V.findViewById(checkerIDs[checkerId]);

            destination.setBackground(ContextCompat.getDrawable(C, R.drawable.light_king_piece));
            source.setBackground(ContextCompat.getDrawable(C, R.drawable.blank_square));
            statusArray[whereToId] = "QLight";
        }

    }

    /**
     * Tikrina ar šaškė gali tapti karaliene
     */
    public boolean CanBeQueen(int whereToId, String color)
    {
        if(color == "Black"){
            for(int i = 56; i < 64; i++) {
                if (checkerIDs[whereToId] == checkerIDs[i])
                {
                    return true;
                }
            }
        }else if(color == "Light") {
            for(int i = 0; i < 8; i++) {
                if (checkerIDs[whereToId] == checkerIDs[i])
                {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Outputina šaškių lentos visų šaškių statusus. Naudojama debuginimui
     */
    public void DebugStatusArray()
    {
        for(int i = 0; i < 8; i++)
        {
            Log.d("myTag", statusArray[(i*8)+0] + " " + statusArray[(i*8)+1] + " " + statusArray[(i*8)+2] + " " + statusArray[(i*8)+3] + " " + statusArray[(i*8)+4] + " " + statusArray[(i*8)+5] + " " + statusArray[(i*8)+6] + " " + statusArray[(i*8)+7] + '\n');
        }

    }

    /**
     * Kirtimo funkcija. Apkeičia šaškę su priešininko šaške
     * ** Pakoreguota funkcija, kad po kirtimo šakė galėtų patapti karaliene, jei yra galima
     */
    public void Kirtimas(View V, Context C, int sourceKirtimasId, int nukirstaId, String kirtimoColor)
    {

        View nukirsta = V.findViewById(checkerIDs[nukirstaId]);

        View source = V.findViewById(checkerIDs[sourceKirtimasId]);

        int destinationId = nukirstaId + (nukirstaId - sourceKirtimasId);
        View destination = V.findViewById(checkerIDs[destinationId]);

        Log.d("destination", Integer.toString(sourceKirtimasId));
        Log.d("nukirsta", Integer.toString(nukirstaId));
        Log.d("source", Integer.toString(destinationId));

        if(kirtimoColor == "Black" || kirtimoColor == "QBlack")
        {
            if(CanBeQueen(destinationId, "Black")){
                destination.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_king_piece));
                statusArray[destinationId] = "QBlack";
                BlackCaptured++;
            }else{
                if(kirtimoColor == "QBlack"){
                    destination.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_king_piece));
                    statusArray[destinationId] = "QBlack";
                }else {
                    destination.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_piece));
                    statusArray[destinationId] = kirtimoColor;
                }
                BlackCaptured++;
            }

        }else if(kirtimoColor == "Light" || kirtimoColor == "QLight")
        {
            if(CanBeQueen(destinationId, "Light")){
                destination.setBackground(ContextCompat.getDrawable(C, R.drawable.light_king_piece));
                statusArray[destinationId] = "QLight";
                WhiteCaptured++;
            }else{
                if(kirtimoColor == "QLight"){
                    destination.setBackground(ContextCompat.getDrawable(C, R.drawable.light_king_piece));
                    statusArray[destinationId] = "QLight";
                }else{
                    destination.setBackground(ContextCompat.getDrawable(C, R.drawable.light_piece));
                    statusArray[destinationId] = kirtimoColor;
                }
                WhiteCaptured++;
            }
        }
        //statusArray[destinationId] = kirtimoColor;

        source.setBackground(ContextCompat.getDrawable(C, R.drawable.blank_square));
        statusArray[sourceKirtimasId] = "0";

        nukirsta.setBackground(ContextCompat.getDrawable(C, R.drawable.blank_square));
        statusArray[nukirstaId] = "0";


    }

    /**
     * Pagrindinė funkcija šaškių paspaudimų valdymui.
     * ** Pakoreguota funkcija. Pridėta karlienės paspaudimo valdymas
     */
    public void CheckerClicked(View checkerView, Context C, View LayoutView, int placeId) {

        if(!pressedStatus)
        {

            switch(statusArray[placeId])
            {
                case "Black":
                    checkerView.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_piece_pressed));

                    statusArray[placeId] = "BlackPressed";
                    pressedStatus = true;
                    pressedId = placeId;

                    HighlightPossibleMoves(LayoutView, C, placeId, "Black");
                    break;

                case "Light":
                    checkerView.setBackground(ContextCompat.getDrawable(C, R.drawable.light_piece_pressed));

                    statusArray[placeId] = "LightPressed";
                    pressedStatus = true;
                    pressedId = placeId;

                    HighlightPossibleMoves(LayoutView, C, placeId, "Light");
                    break;
                case "QBlack": //karalienes paspaudimas
                    checkerView.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_piece_pressed));
                    statusArray[placeId] = "QBlackPressed";
                    pressedStatus = true;
                    pressedId = placeId;

                    HighlightPossibleMoves(LayoutView, C, placeId, "QBlack");
                    break;
                case "QLight": //karalienes paspaudimas
                    checkerView.setBackground(ContextCompat.getDrawable(C, R.drawable.light_piece_pressed));
                    statusArray[placeId] = "QLightPressed";
                    pressedStatus = true;
                    pressedId = placeId;

                    HighlightPossibleMoves(LayoutView, C, placeId, "QLight");
                    break;
            }
        }else{
            switch(statusArray[placeId]) {
                case "BlackPressed":
                    checkerView.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_piece));

                    statusArray[placeId] = "Black";
                    pressedStatus = false;
                    pressedId = -1;

                    removeHighlightedMoves(LayoutView, C);

                    break;

                case "LightPressed":
                    checkerView.setBackground(ContextCompat.getDrawable(C, R.drawable.light_piece));

                    statusArray[placeId] = "Light";
                    pressedStatus = false;
                    pressedId = -1;

                    removeHighlightedMoves(LayoutView, C);
                    break;
                case "QLightPressed":
                    checkerView.setBackground(ContextCompat.getDrawable(C, R.drawable.light_king_piece));

                    statusArray[placeId] = "QLight";
                    pressedStatus = false;
                    pressedId = -1;

                    removeHighlightedMoves(LayoutView, C);
                    break;
                case "QBlackPressed":
                    checkerView.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_king_piece));

                    statusArray[placeId] = "QBlack";
                    pressedStatus = false;
                    pressedId = -1;

                    removeHighlightedMoves(LayoutView, C);
                    break;
                case "AvailableMove":
                    MoveCheckerPiece(LayoutView, C, pressedId, placeId);
                    View switch_button = LayoutView.findViewById(R.id.switch_sides);
                    switch_button.setVisibility(View.INVISIBLE);
                    move_count =  move_count +1;

                    removeHighlightedMoves(LayoutView, C);//
                    statusArray[pressedId] = "0";
                    pressedStatus = false;
                    pressedId = -1;

                    break;
                case "QBlack":
                case "Black":

                    if(statusArray[pressedId] == "LightPressed")
                    {

                        int[] possibleMoves = FindAllPossibleKirtimai(pressedId, statusArray[pressedId]);
                        for(int i = 0; i < 40; i++)
                        {

                            if(possibleMoves[i] != -1)
                            {
                                if(possibleMoves[i] == placeId)
                                {
                                    Kirtimas(LayoutView, C, pressedId, placeId, "Light");
                                    removeHighlightedMoves(LayoutView, C);
                                    pressedStatus = false;
                                    pressedId = -1;
                                }
                            }
                        }
                    }else if(statusArray[pressedId] == "QLightPressed"){
                        int[] possibleMoves = FindAllPossibleKirtimai(pressedId, statusArray[pressedId]);
                        for(int i = 0; i < 40; i++)
                        {

                            if(possibleMoves[i] != -1)
                            {
                                if(possibleMoves[i] == placeId)
                                {
                                    Kirtimas(LayoutView, C, pressedId, placeId, "QLight");
                                    removeHighlightedMoves(LayoutView, C);
                                    pressedStatus = false;
                                    pressedId = -1;
                                }
                            }
                        }
                    }
                    break;
                case "QLight":
                case "Light":
                    //Log.d("test", statusArray[pressedId]);
                    if(statusArray[pressedId] == "BlackPressed")
                    {

                        int[] possibleMoves = FindAllPossibleKirtimai(pressedId, statusArray[pressedId]);

                        for(int i = 0; i < 40; i++)
                        {
                            if(possibleMoves[i] != -1) {
                                if (possibleMoves[i] == placeId) {
                                    Kirtimas(LayoutView, C, pressedId, placeId, "Black");
                                    removeHighlightedMoves(LayoutView, C);
                                    pressedStatus = false;
                                    pressedId = -1;
                                }
                            }
                        }
                    }else if(statusArray[pressedId] == "QBlackPressed"){
                        int[] possibleMoves = FindAllPossibleKirtimai(pressedId, statusArray[pressedId]);

                        for(int i = 0; i < 40; i++)
                        {
                            if(possibleMoves[i] != -1) {
                                if (possibleMoves[i] == placeId) {
                                    Kirtimas(LayoutView, C, pressedId, placeId, "QBlack");
                                    removeHighlightedMoves(LayoutView, C);
                                    pressedStatus = false;
                                    pressedId = -1;
                                }
                            }
                        }
                    }
                    break;

            }
        }
        DebugStatusArray();
    }



    //----------------------------------------------------------------------------------------------------------------------------------

    /**
     * Atvirkštinė logika
     */


    boolean IsEnemyChecker2(int placeId, String color)
    {
        if(color == "Light" || color == "QLight")
        {
            if(statusArray[placeId] == "Black" || statusArray[placeId] == "QBlack")
            {
                return true;
            }
        }else if (color == "Black" || color == "QBlack")
        {
            if(statusArray[placeId] == "Light" || statusArray[placeId] == "QLight")
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Suranda visus galimus kirtimus
     * ** Pakoreguota funkcija. Suranda ir karalienes galimus kirtimus
     */
    int[] FindAllPossibleKirtimai2(int placeId, String color) {
        int[] possibleMoves = new int[40];
        Arrays.fill(possibleMoves, -1);

        int checkerRow = (placeId) / 8;

        possibleMoves[0] = placeId - 9;
        possibleMoves[1] = placeId - 7;
        possibleMoves[2] = placeId + 7;
        possibleMoves[3] = placeId + 9;

        for (int i = 0; i < 40; i++) {
            if(possibleMoves[i] != -1)
            {
                if((possibleMoves[i]) / 8 != checkerRow + 1){
                    if((possibleMoves[i]) / 8 != checkerRow - 1) {
                        possibleMoves[i] = -1;
                    }
                }
            }
        }

        for(int i = 0; i < 40; i++)
        {
            Log.d("t", Integer.toString(possibleMoves[i]));
            if(color == "Light" || color == "QLight")
            {
                if(statusArray[possibleMoves[i]] != "Black")
                {
                    possibleMoves[i] = -1;
                }
            }else if(color == "Black" || color == "QBlack")
            {
                if(statusArray[possibleMoves[i]] != "Light")
                {
                    possibleMoves[i] = -1;
                }
            }
        }

        for(int i = 0; i < 40; i++) {
            if (possibleMoves[i] + (possibleMoves[i] - placeId) < 0 || possibleMoves[i] + (possibleMoves[i] - placeId) > 63) {
                possibleMoves[i] = -1;
            }
        }

        for(int i = 0; i < 40; i++)
        {
            if(possibleMoves[i] != -1)
            {

                if(statusArray[possibleMoves[i] + (possibleMoves[i] - placeId)] != "0")
                {
                    possibleMoves[i] = -1;
                }

            }


        }



        return possibleMoves;

    }


    int[] FindAllPossibleMoves2(int placeId, String color)
    {
        int[] possibleMoves = new int[40];
        Arrays.fill(possibleMoves, -1);

        int checkerRow = (placeId) / 8;


        if(color == "Light")
        {
            possibleMoves[0] = placeId + 9;
            possibleMoves[1] = placeId + 7;
        }else if(color == "Black")
        {
            possibleMoves[0] = placeId - 9;
            possibleMoves[1] = placeId - 7;
        }else if(color == "QLight"){
            possibleMoves[0] = placeId + 9;
            possibleMoves[1] = placeId + 7;
            possibleMoves[2] = placeId - 9;
            possibleMoves[3] = placeId - 7;
        }else if(color == "QBlack"){
            possibleMoves[0] = placeId - 9;
            possibleMoves[1] = placeId - 7;
            possibleMoves[2] = placeId + 9;
            possibleMoves[3] = placeId + 7;
        }

        for(int i = 0; possibleMoves[i] != -1; i++)
        {
            if(possibleMoves[i] > 63)
            {
                possibleMoves[i] = -1;
            }else if(possibleMoves[i] < 0)
            {
                possibleMoves[i] = -1;
            }

            if(possibleMoves[i] != -1)
            {
                if(color == "Light")
                {
                    if((possibleMoves[i]) / 8 != checkerRow + 1)
                    {
                        possibleMoves[i] = -1;
                    }
                }else if(color == "Black")
                {
                    if((possibleMoves[i]) / 8 != checkerRow - 1)
                    {
                        possibleMoves[i] = -1;
                    }
                }
            }
        }
        return possibleMoves;
    }

    /**
     * Šaškės judėjimo funkcija.
     * ** Pakoreguota funkcija, ėjimo metu tikrina ar figūrėlė gali tapti karaliene, jei taip
     * ** figurėlė patampa karaliene(QLight/QBlack).
     * ** Pridėtas karalienės judėjimas lentoje
     */
    public void MoveCheckerPiece2(View V, Context C, int checkerId, int whereToId)
    {

        if(statusArray[checkerId] == "LightPressed"){

            View destination = V.findViewById(checkerIDs[whereToId]);
            View source = V.findViewById(checkerIDs[checkerId]);

            boolean queen = CanBeQueen(whereToId, "Light");
            if(queen){
                destination.setBackground(ContextCompat.getDrawable(C, R.drawable.light_king_piece));
                source.setBackground(ContextCompat.getDrawable(C, R.drawable.blank_square));
                statusArray[whereToId] = "QLight";
            }else{
                destination.setBackground(ContextCompat.getDrawable(C, R.drawable.light_piece));
                source.setBackground(ContextCompat.getDrawable(C, R.drawable.blank_square));
                statusArray[whereToId] = "Light";
            }
        }else if(statusArray[checkerId] == "BlackPressed")
        {
            View destination = V.findViewById(checkerIDs[whereToId]);
            View source = V.findViewById(checkerIDs[checkerId]);
            boolean queen = CanBeQueen(whereToId, "Black");
            if(queen){
                Log.d("myTag", "to queen method\n");
                destination.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_king_piece));
                source.setBackground(ContextCompat.getDrawable(C, R.drawable.blank_square));
                statusArray[whereToId] = "QBlack";
            }
            else{
                destination.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_piece));
                source.setBackground(ContextCompat.getDrawable(C, R.drawable.blank_square));
                statusArray[whereToId] = "Black";
            }
        }else if(statusArray[checkerId] == "QLightPressed"){
            View destination = V.findViewById(checkerIDs[whereToId]);
            View source = V.findViewById(checkerIDs[checkerId]);

            destination.setBackground(ContextCompat.getDrawable(C, R.drawable.light_king_piece));
            source.setBackground(ContextCompat.getDrawable(C, R.drawable.blank_square));
            statusArray[whereToId] = "QLight";
        }else if(statusArray[checkerId] == "QBlackPressed"){
            View destination = V.findViewById(checkerIDs[whereToId]);
            View source = V.findViewById(checkerIDs[checkerId]);

            destination.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_king_piece));
            source.setBackground(ContextCompat.getDrawable(C, R.drawable.blank_square));
            statusArray[whereToId] = "QBlack";
        }

    }






    public void Kirtimas2(View V, Context C, int sourceKirtimasId, int nukirstaId, String kirtimoColor)
    {

        View nukirsta = V.findViewById(checkerIDs[nukirstaId]);

        View source = V.findViewById(checkerIDs[sourceKirtimasId]);

        int destinationId = nukirstaId + (nukirstaId - sourceKirtimasId);
        View destination = V.findViewById(checkerIDs[destinationId]);

        Log.d("destination", Integer.toString(sourceKirtimasId));
        Log.d("nukirsta", Integer.toString(nukirstaId));
        Log.d("source", Integer.toString(destinationId));

        if(kirtimoColor == "Light")
        {
            destination.setBackground(ContextCompat.getDrawable(C, R.drawable.light_piece));
            BlackCaptured++;

        }else if(kirtimoColor == "Black")
        {
            destination.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_piece));
            WhiteCaptured++;
        }
        statusArray[destinationId] = kirtimoColor;

        source.setBackground(ContextCompat.getDrawable(C, R.drawable.blank_square));
        statusArray[sourceKirtimasId] = "0";

        nukirsta.setBackground(ContextCompat.getDrawable(C, R.drawable.blank_square));
        statusArray[nukirstaId] = "0";


    }





    public void CheckerClicked2(View checkerView, Context C, View LayoutView, int placeId) {

        if(!pressedStatus)
        {

            switch(statusArray[placeId])
            {
                case "Light":
                    checkerView.setBackground(ContextCompat.getDrawable(C, R.drawable.light_piece_pressed));

                    statusArray[placeId] = "LightPressed";
                    pressedStatus = true;
                    pressedId = placeId;

                    HighlightPossibleMoves2(LayoutView, C, placeId, "Light");
                    break;

                case "Black":
                    checkerView.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_piece_pressed));

                    statusArray[placeId] = "BlackPressed";
                    pressedStatus = true;
                    pressedId = placeId;

                    HighlightPossibleMoves2(LayoutView, C, placeId, "Black");
                    break;
                case "QLight": //karalienes paspaudimas
                    checkerView.setBackground(ContextCompat.getDrawable(C, R.drawable.light_piece_pressed));
                    statusArray[placeId] = "QLightPressed";
                    pressedStatus = true;
                    pressedId = placeId;

                    HighlightPossibleMoves2(LayoutView, C, placeId, "QLight");
                    break;
                case "QBlack": //karalienes paspaudimas
                    checkerView.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_piece_pressed));
                    statusArray[placeId] = "QBlackPressed";
                    pressedStatus = true;
                    pressedId = placeId;

                    HighlightPossibleMoves2(LayoutView, C, placeId, "QBlack");
                    break;
            }
        }else{
            switch(statusArray[placeId]) {
                case "LightPressed":
                    checkerView.setBackground(ContextCompat.getDrawable(C, R.drawable.light_piece));

                    statusArray[placeId] = "Light";
                    pressedStatus = false;
                    pressedId = -1;

                    removeHighlightedMoves(LayoutView, C);

                    break;

                case "BlackPressed":
                    checkerView.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_piece));

                    statusArray[placeId] = "Black";
                    pressedStatus = false;
                    pressedId = -1;

                    removeHighlightedMoves(LayoutView, C);
                    break;
                case "QBlackPressed":
                    checkerView.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_king_piece));

                    statusArray[placeId] = "QBlack";
                    pressedStatus = false;
                    pressedId = -1;

                    removeHighlightedMoves(LayoutView, C);
                    break;
                case "QLightPressed":
                    checkerView.setBackground(ContextCompat.getDrawable(C, R.drawable.light_king_piece));

                    statusArray[placeId] = "QLight";
                    pressedStatus = false;
                    pressedId = -1;

                    removeHighlightedMoves(LayoutView, C);
                    break;
                case "AvailableMove":
                    MoveCheckerPiece2(LayoutView, C, pressedId, placeId);
                    View switch_button = LayoutView.findViewById(R.id.switch_sides);
                    switch_button.setVisibility(View.INVISIBLE);
                    move_count= move_count + 1;
                    removeHighlightedMoves(LayoutView, C);//
                    statusArray[pressedId] = "0";
                    pressedStatus = false;
                    pressedId = -1;

                    break;
                case "Light":

                    if(statusArray[pressedId] == "BlackPressed")
                    {

                        int[] possibleMoves = FindAllPossibleKirtimai2(pressedId, statusArray[pressedId]);
                        for(int i = 0; i < 40; i++)
                        {

                            if(possibleMoves[i] != -1)
                            {
                                if(possibleMoves[i] == placeId)
                                {
                                    Kirtimas2(LayoutView, C, pressedId, placeId, "Black");
                                    removeHighlightedMoves(LayoutView, C);
                                    pressedStatus = false;
                                    pressedId = -1;
                                }
                            }
                        }
                    }
                    break;
                case "Black":
                    //Log.d("test", statusArray[pressedId]);
                    if(statusArray[pressedId] == "LightPressed")
                    {

                        int[] possibleMoves = FindAllPossibleKirtimai2(pressedId, statusArray[pressedId]);

                        for(int i = 0; i < 40; i++)
                        {
                            if(possibleMoves[i] != -1) {
                                if (possibleMoves[i] == placeId) {
                                    Kirtimas2(LayoutView, C, pressedId, placeId, "Light");
                                    removeHighlightedMoves(LayoutView, C);
                                    pressedStatus = false;
                                    pressedId = -1;
                                }
                            }
                        }
                    }
                    break;
            }
        }
        DebugStatusArray();
        EveryCheckerPossibleMoves(2);
    }


    public void HighlightPossibleMoves2(View V, Context C, int placeId, String color)
    {
        int[] possibleMoves = FindAllPossibleMoves2(placeId, color);

        for(int i = 0; i < 40; i++)
        {
            if( possibleMoves[i] != -1)
            {

                if(IsPlaceFree(possibleMoves[i], color))
                {
                    if(IsEnemyChecker(possibleMoves[i], color))
                    {

                    }else{
                        View testV = V.findViewById(checkerIDs[possibleMoves[i]]);

                        testV.setBackground(ContextCompat.getDrawable(C, R.drawable.available_move));

                        statusArray[possibleMoves[i]] = "AvailableMove";
                    }
                }
            }
        }
    }





}
