package com.example.franciscocastillo.mergesort;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ListView sortedLV, originalLV;
    private ArrayAdapter<String> sortedAA, unsortedAA;
    private int[]  sortedNumbers, unsortedNumbers;
    private String[] unsortedString, sortedString;
    private final int numberOfElements = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.sortedLV =  this.findViewById(R.id.sortedLV);
        this.originalLV = this.findViewById(R.id.originalLV);

        this.unsortedNumbers = new int [this.numberOfElements];
        this.sortedNumbers = new int [this.numberOfElements];

        this.sortedString = new String[this.numberOfElements];
        this.unsortedString = new String[this.numberOfElements];

        this.sortedAA = new ArrayAdapter<String>(this,R.layout.simple_listview_row,this.sortedString);
        this.unsortedAA = new ArrayAdapter<String>(this,R.layout.simple_listview_row,this.unsortedString);

        this.sortedLV.setAdapter(this.sortedAA);
        this.originalLV.setAdapter(this.unsortedAA);

        this.fillRandomntArray(this.unsortedNumbers);
        this.copyContentOfIntArray(this.unsortedNumbers, this.sortedNumbers);

        this.initializeArrays();

    }
    private void insertionSort(int[] r){
        int follower,swap;
        for(int currstart = 0; currstart < r.length; currstart ++){
            follower = currstart;
            while( follower > 0 && r[follower]<r[follower-1]){
                swap = r[follower];
                r[follower] = r[follower-1];
                r[follower-1] = swap;
                follower--;
            }
        }
    }
    public void resetButtonPressed(View v){
        this.initializeArrays();
    }
    public void insertionButtonPressed(View v){
        this.insertionSort(this.unsortedNumbers);
        this.updateStringArrays();
    }
    public void sortHelper(int[] r, int start, int end){
        if (start != end){
            // not sure how to recursively divide the list
            // looked at python code
            int half = (end + start) / 2;
            this.sortHelper(r, start, half -1);
            sortHelper(r, half, end);
        }
    }

    public void mergerSort(int[] r){
        this.sortHelper(r, 0, r.length-1);
    }

    public void mergeSortButtonPressed(View v){
        this.insertionSort(this.unsortedNumbers);
        this.updateStringArrays();
    }

    private void updateStringArrays(){
        this.copyIntToString(this.unsortedNumbers,this.unsortedString);
        this.copyIntToString(this.sortedNumbers,this.sortedString);
        this.sortedAA.notifyDataSetChanged();
        this.unsortedAA.notifyDataSetChanged();
    }
    private void initializeArrays(){
        this.fillRandomntArray(this.sortedNumbers);
        this.copyContentOfIntArray(this.unsortedNumbers,this.sortedNumbers);
        this.updateStringArrays();
    }
    private void copyIntToString(int[] arInts,String[] arStrings){
        for (int i = 0; i< arInts.length; i++){
            arStrings[i] = ""+ arInts[i];
        }
    }
    private void copyContentOfIntArray(int[] destination, int[] source){
        for (int i = 0; i< source.length; i++){
            destination[i] = source[i];
        }
    }
    private void fillRandomntArray(int[] ar){
        Random r = new Random();

        for(int i= 0; i< ar.length;i++){
            ar[i] = r.nextInt(500);
        }
    }
}
