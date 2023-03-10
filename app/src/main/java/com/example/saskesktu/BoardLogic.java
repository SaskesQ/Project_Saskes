package com.example.saskesktu;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.core.content.ContextCompat;

import java.util.Arrays;

import kotlin.collections.IntIterator;

public class BoardLogic {
    String[] statusArray; //Visų šaškių statusai
    int[] checkerIDs; //Visų šaškių ID. Naudojamas kartu su statusArray
    boolean pressedStatus; //1 - Šaškė paspausta; 0 - Šaškė nepaspausta
    int pressedId; //Paspaustos šaškės id.
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
     */
    boolean IsPlaceFree(int placeId, String color)
    {
        if(statusArray[placeId] == color)
        {
            return false;
        }else{
            return true;
        }
    }

    /**
     * Patikrina, ar langelis yra priešo šaškė
     */
    boolean IsEnemyChecker(int placeId, String color)
    {
        if(color == "Black")
        {
            if(statusArray[placeId] == "Light")
            {
                return true;
            }
        }else if (color == "Light")
        {
            if(statusArray[placeId] == "Black")
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Suranda visus galimus kirtimus
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
            if(color == "Black")
            {
                if(statusArray[possibleMoves[i]] != "Light")
                {
                    possibleMoves[i] = -1;
                }
            }else if(color == "Light")
            {
                if(statusArray[possibleMoves[i]] != "Black")
                {
                    possibleMoves[i] = -1;
                }
            }

        }
        return possibleMoves;

    }

    /**
     * Suranda visus galimus judėti langelius
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
     */
    public void MoveCheckerPiece(View V, Context C, int checkerId, int whereToId)
    {

        if(statusArray[checkerId] == "BlackPressed"){

            View destination = V.findViewById(checkerIDs[whereToId]);

            destination.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_piece));

            View source = V.findViewById(checkerIDs[checkerId]);

            source.setBackground(ContextCompat.getDrawable(C, R.drawable.blank_square));

            statusArray[whereToId] = "Black";

        }else if(statusArray[checkerId] == "LightPressed")
        {
            View destination = V.findViewById(checkerIDs[whereToId]);

            destination.setBackground(ContextCompat.getDrawable(C, R.drawable.light_piece));

            View source = V.findViewById(checkerIDs[checkerId]);

            source.setBackground(ContextCompat.getDrawable(C, R.drawable.blank_square));

            statusArray[whereToId] = "Light";

        }

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
     */
    public void Kirtimas(View V, Context C, int sourceKirtimasId, int destKirtimasId, String kirtimoColor)
    {
        View destination = V.findViewById(checkerIDs[destKirtimasId]);
        View source = V.findViewById(checkerIDs[sourceKirtimasId]);

        if(kirtimoColor == "Black")
        {
            destination.setBackground(ContextCompat.getDrawable(C, R.drawable.dark_piece));

        }else if(kirtimoColor == "Light")
        {
            destination.setBackground(ContextCompat.getDrawable(C, R.drawable.light_piece));
        }
        statusArray[destKirtimasId] = kirtimoColor;

        source.setBackground(ContextCompat.getDrawable(C, R.drawable.blank_square));

        statusArray[sourceKirtimasId] = "0";

    }

    /**
     * Pagrindinė funkcija šaškių paspaudimų valdymui.
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
                case "AvailableMove":
                    MoveCheckerPiece(LayoutView, C, pressedId, placeId);
                    removeHighlightedMoves(LayoutView, C);//
                    statusArray[pressedId] = "0";
                    pressedStatus = false;
                    pressedId = -1;

                    break;
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
                    }
                    break;
                case "Light":
                    Log.d("test", statusArray[pressedId]);
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
                    }
                    break;
            }
        }
        DebugStatusArray();
    }
}
