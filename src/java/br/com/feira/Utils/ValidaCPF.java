/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.feira.Utils;


/**
 *
 * @author vagner.gomes
 */
public class ValidaCPF {
    
    public boolean validaCPF(String cpf){
        String l = "";
        for(int n = 0; n <= 9; n++){
            for(int j = 0; j < 11; j++){
                l = l + String.valueOf(n);
            }
            String v = l.substring(l.length() - 11);
            //System.out.println(v);
            if(cpf.equals(v) || cpf.length() != 11)
                //System.out.println("Inválido1");
                return(false);
        }  
        
        char dgt10, dgt11;
        int m = 10;
        int total = 0;
        int r, s;
       
        //1º digito verificador 
        s = 0;
        for (int i = 0; i < 9; i++) {
            int num = (int) cpf.charAt(i) - 48;
            total = total + (num * m);
            //System.out.println(num+"x"+m+"="+total+" - "+m);
            m = m - 1;   
        }
        r = 11 - (total % 11);
        if(r > 9)
            dgt10 = '0';
        else
            dgt10 = (char)(r + 48);
        
        //System.out.println(dgt10);
        
        //2º digito verificador 
        m = 11;
        s = 0;
        total = 0;
        for(int i = 0; i < 10; i++){
            int num = (int) cpf.charAt(i) - 48;
            total = total + (num * m);
            //System.out.println(num+"x"+m+"="+total+" - "+m);
            m = m - 1;
        }
        r = 11 - (total % 11);
        if(r > 9)
            dgt11 = '0';
        else
            dgt11 = (char) (r + 48);
        
       // System.out.println(dgt11);
        
        if(dgt10 == cpf.charAt(9) && dgt11 == cpf.charAt(10))
            //System.out.println("Válido");
            return(true);
        else
            //System.out.println("Inválido");
            return(false);
    }
    
  /*  public static void main(String[] args) {
        //String cpf = "03241461670"; //válido
        //String cpf = "10594508606"; //válido
        //String cpf = "11253202656"; //válido
        //String cpf = "00000000000"; //inválido
        String cpf = "11253202656";
        
        System.out.println(validaCPF(cpf));
    }
*/
}
