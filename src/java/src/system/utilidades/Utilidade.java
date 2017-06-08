/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.system.utilidades;

/**
 *
 * @author admin
 */
public class Utilidade {

    public static String graduacaoParaString(int grad) {
        String gradRetorno;
        switch (grad) {
            case 0:
                gradRetorno = "SC";
                break;
            case 1:
                gradRetorno = "MN-RC";
                break;
            case 2:
                gradRetorno = "MN";
                break;
            case 3:
                gradRetorno = "MNE";
                break;
            case 4:
                gradRetorno = "CB";
                break;
            case 5:
                gradRetorno = "3SG";
                break;
            case 6:
                gradRetorno = "2SG";
                break;
            case 7:
                gradRetorno = "1SG";
                break;
            case 8:
                gradRetorno = "SO";
                break;
            case 9:
                gradRetorno = "GM";
                break;
            case 10:
                gradRetorno = "1T";
                break;
            case 11:
                gradRetorno = "2T";
                break;
            case 12:
                gradRetorno = "CT";
                break;
            case 13:
                gradRetorno = "CC";
                break;
            case 14:
                gradRetorno = "CF";
                break;
            case 15:
                gradRetorno = "CMG";
                break;
            case 16:
                gradRetorno = "CA";
                break;
            case 17:
                gradRetorno = "VA";
                break;
            case 18:
                gradRetorno = "AE";
                break;
            default:
                gradRetorno = "GRAD_VAZIA";
                break;
        }
        return gradRetorno;
    }

    public static int graduacaoParaInt(String grad) {
        int gradRetorno;
        switch (grad) {
            case "SC":
                gradRetorno = 0;
                break;
            case "MN-RC":
                gradRetorno = 1;
                break;
            case "MN":
                gradRetorno = 2;
                break;
            case "GRAD3":
                gradRetorno = 3;
                break;
            case "CB":
                gradRetorno = 4;
                break;
            case "3ºSG":
                gradRetorno = 5;
                break;
            case "2ºSG":
                gradRetorno = 6;
                break;
            case "1ºSG":
                gradRetorno = 7;
                break;
            case "SO":
                gradRetorno = 8;
                break;
            case "GM":
                gradRetorno = 9;
                break;
            case "1ºT":
                gradRetorno = 10;
                break;
            case "2ºT":
                gradRetorno = 11;
                break;
            case "CT":
                gradRetorno = 12;
                break;
            case "CC":
                gradRetorno = 13;
                break;
            case "CF":
                gradRetorno = 14;
                break;
            case "CMG":
                gradRetorno = 15;
                break;
            case "CA":
                gradRetorno = 16;
                break;
            case "VA":
                gradRetorno = 17;
                break;
            case "AE":
                gradRetorno = 18;
                break;
            default:
                gradRetorno = 0;
                break;
        }
        return gradRetorno;
    }

    public static String omParaString(int om) {
        String omRetorno;
        switch (om) {
            case 1:
                omRetorno = "BHMN";
                break;
            case 2:
                omRetorno = "DHN";
                break;
            case 3:
                omRetorno = "GNHO";
                break;
            case 4:
                omRetorno = "CAMR";
                break;
            case 5:
                omRetorno = "CHM";
                break;
            case 6:
                omRetorno = "NApOc Ary Rongel";
                break;
            case 7:
                omRetorno = "NHi Sirius";
                break;
            case 8:
                omRetorno = "NHo amorim do Vale";
                break;
            case 9:
                omRetorno = "NHo Cruzeiro do Sul";
                break;
            case 10:
                omRetorno = "NHo Taurus";
                break;
            case 11:
                omRetorno = "NHoF Graça Aranha";
                break;
            case 12:
                omRetorno = "NOc Antares";
                break;
            case 13:
                omRetorno = "NPo Maximiano";
                break;
            case 14:
                omRetorno = "NPqHo Vital de Oliveira";
                break;
            default:
                omRetorno = "ERROR";
                break;
        }
        return omRetorno;
    }

    public static int omParaInt(String om) {
        int omRetorno;
        switch (om) {
            case "BHMN":
                omRetorno = 1;
                break;
            case "DHN":
                omRetorno = 2;
                break;
            case "GNHO":
                omRetorno = 3;
                break;
            case "CAMR":
                omRetorno = 4;
                break;
            case "CHM":
                omRetorno = 5;
                break;
            case "NApOc Ary Rongel":
                omRetorno = 6;
                break;
            case "NHi Sirius":
                omRetorno = 7;
                break;
            case "NHo amorim do Vale":
                omRetorno = 8;
                break;
            case "NHo Cruzeiro do Sul":
                omRetorno = 9;
                break;
            case "NHo Taurus":
                omRetorno = 10;
                break;
            case "NHoF Graça Aranha":
                omRetorno = 11;
                break;
            case "NOc Antares":
                omRetorno = 12;
                break;
            case "NPo Maximiano":
                omRetorno = 13;
                break;
            case "NPqHo Vital de Oliveira":
                omRetorno = 14;
                break;
            default:
                omRetorno = -1;
                break;
        }
        return omRetorno;
    }

    public static String estadoCivilParaString(int estadoInt) {
        String retorno = "";
        switch (estadoInt) {
            case 1:
                retorno = "Solteiro(a)";
                break;
            case 2:
                retorno = "Casado(a)";
                break;
            case 3:
                retorno = "Divorciado(a)";
                break;
            case 4:
                retorno = "Viúvo(a)";
                break;
            case 5:
                retorno = "Separado(a)";
                break;
            case 6:
                retorno = "Companheiro(a)";
                break;
        }
        return retorno;
    }
}
