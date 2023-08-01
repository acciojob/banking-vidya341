package com.driver;

import java.util.PriorityQueue;

class pair{
    int occ;
    char ch;
    pair(int occ,char ch)
    {
        this.occ = occ;
        this.ch = ch;
    }
}

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only


    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        this.tradeLicenseId = tradeLicenseId;

    }
    public boolean check_valid(String trade_id)
    {
        for(int i=1;i<trade_id.length();i++)
        {
            if(trade_id.charAt(i)==trade_id.charAt(i-1))
            {
                return false;
            }
        }
        return true;
    }

    PriorityQueue<pair> pq = new PriorityQueue<>((a, b)->
    {
        return b.occ-a.occ;
    });
    public int get_max_count(int []freq) {

        int count = 0;
        int max = -1;
        int max_val = -1;
        for (int i = 0; i < 26; i++) {
            count = freq[i];
            if (count > 0) pq.add(new pair(count, (char) (i + 'A')));
            if (max < count) {
                max = count;
                max_val = i;
            }
        }
        return max_val;
    }
        public String reorganize(String s)
    {
        int n = s.length();
        int []freq = new int[26];//store the freq count
        for(int i=0;i<n;i++)
        {
            char ch = s.charAt(i);
            freq[ch-'A']++;

        }
        int max = get_max_count(freq);//get the index with most freq char
        char max_ch = (char)(max+'A');//get char with most frequency
        int max_no = freq[max];//get max freq count
        if(max_no>(n+1)/2)//if max freq count of specific character is greater than half of length of string then not possible to reorganize
        {
            return "";
        }
        char[]ans = new char[n];
        int ind=0;//first fill the even positions then fill odd positions to maintain the gap between two similar characters
        while(pq.size()!=0)//get all the character in decreasing order of their occurences
        {
            pair temp = pq.remove();
            int occ = temp.occ;
            char ch = temp.ch;
            while(occ!=0)
            {
                while(occ!=0 && ind<n)
                {
                    ans[ind] = ch;
                    ind+=2;
                    occ--;

                }
                if(ind>=n) ind=1;//if ind goes beyond n then come back to index 1 and start filing odd positions
            }


        }
        String f_ans = "";
        for(int i=0;i<n;i++)
        {
            f_ans += ans[i];
        }
        return f_ans;

    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(!check_valid(this.tradeLicenseId))
        {
            String new_str = reorganize(tradeLicenseId);
            if(new_str=="")
            {

                throw new Exception("Valid License can not be generated");

            }
            else {
                this.tradeLicenseId = new_str;
            }

        }


    }

}
