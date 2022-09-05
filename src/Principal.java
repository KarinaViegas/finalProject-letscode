import java.util.Scanner;

public class Principal {
    private static double getTemp() {
        Scanner input = new Scanner(System.in);
        System.out.println("|_______________________________________________________________|");
        System.out.println("|Digite uma temperatura para ser convertida:                    |");
        return input.nextDouble();
    }

    private static int getQtdInteracoes() {
        Scanner input = new Scanner(System.in);
        System.out.println("|________________________________________________________________|");
        System.out.print("|Digite a quantidade de interações que deseja realizar   ->      ");
        return input.nextInt();
    }

    private static void printMenu(String nomeQualquerDeVariavel){
        System.out.println("_________________________________________________________________");
        System.out.println("|Digite a unidade de temperatura de " + nomeQualquerDeVariavel+"                     |");
        System.out.println("|_________________Menu__________________________________________|");
        System.out.println("|      Digite as opções em maiscula:                            |");
        System.out.println("|_______________________________________________________________|");
        System.out.println("| Opção - CELSIUS                                               |");
        System.out.println("| Opção - FAHRENHEIT                                            |");
        System.out.println("| Opção - KELVIN.                                               |");
        System.out.println("|_______________________________________________________________|");
    };

    public static void main(String[] args) {
        double[] resultados, valoresIniciais;
        initialize();
        int qtdInteracores;
        int qtdErros = 0;

        qtdInteracores = getQtdInteracoes();
        resultados = new double[qtdInteracores];
        valoresIniciais = new double[qtdInteracores];
        for (int i = 0; i < qtdInteracores; i++) {
            try {


                UnityTemp unityInput = getUnityTemp("entrada");
                if (unityInput == null) {
                    qtdErros++;
                    continue;
                }
                UnityTemp unityOutput = getUnityTemp("saída");
                if (unityOutput == null) {
                    qtdErros++;
                    continue;
                }
                double temp = getTemp();
                valoresIniciais[i] = temp;

                System.out.println("| Você vai transformar " + temp + " " + unityInput + " em " + unityOutput +"                |");

                switch (unityOutput) {
                    case CELSIUS:
                        resultados[i] = toCelsiusTransform(unityInput, temp);
                        break;

                    case FAHRENHEIT:
                        resultados[i] = toFahrenheitTransform(unityInput, temp);
                        break;

                    case KELVIN:
                        resultados[i] = toKelvinTransform(unityInput, temp);
                        break;

                    default:
                        System.out.println("_________________________________________________________________");
                        System.out.println("| Nao conseguir entender para qual temperatura deseja converter |");
                        break;
                }

                System.out.printf("|O resultado da conversão de %.2f %s para %s é -> %.2f|", temp, unityInput, unityOutput, resultados[i]);
                System.out.println();

            } catch (Exception e){
                System.out.println("Erro nao Mapeado");
                System.out.println();
            }
        }

        double somaTemperaturasTransformadas=0, somaTemperaturasIniciais=0;
        double mediaTemperaturaTransformadas, mediaTemperaturaIniciais = 0;
        for (int i = 0; i < qtdInteracores; i++) {
            somaTemperaturasTransformadas += resultados[i];
            somaTemperaturasIniciais += valoresIniciais[i];
        }
        mediaTemperaturaTransformadas = somaTemperaturasTransformadas/(qtdInteracores-qtdErros);
        mediaTemperaturaIniciais = somaTemperaturasIniciais/(qtdInteracores-qtdErros);
        System.out.printf("\n| O valor médio das temperaturas Iniciais, eliminando as que apresentaram erro foi %.2f |", mediaTemperaturaIniciais);
        System.out.println("|                                                                                         |");
        System.out.printf("\n| O valor médio das temperaturas transformadas, eliminando as que apresentaram erro foi %.2f |", mediaTemperaturaTransformadas);
        System.out.println("|                                                                                         |");
        System.out.println("__________________ ISSO É TUDO PESSOAL ____________________________________________________");

    }

    private static UnityTemp getUnityTemp(String nomeQualquerDeVariavel) {
        try {
            Scanner input = new Scanner(System.in);
            printMenu(nomeQualquerDeVariavel);
            String typeString = input.nextLine();
            return UnityTemp.valueOf(typeString);
        } catch (Exception e){
            System.out.println("|_______________________________________________________________|");
            System.out.println("Unidade de temperatua invalida, /n Consigo converter em CELSIUS, FAHRENHEIT e KELVIN;");
            System.out.println("Mas essa tentativa sera eliminada");
            return null;
        }
    }

    private static void initialize() {
        System.out.println("__________________________________________________________________");
        System.out.println("|Bem vindo ao nosso conversor de temperaturas.                   |");
        System.out.println("|Nele você pode converter quantas vezes quiser suas temperaturas |");

    }

    // retorna temperatura em fahrenheit
    public static double toFahrenheitTransform(UnityTemp type, double temp) {
        if (type == UnityTemp.CELSIUS) {
            return (temp*9/5)+32;
        } else if (type == UnityTemp.KELVIN) {
            return (temp - 273.15)*9/5 + 32;
        } else {
            return temp;
        }

    }

    //retorna temperatura em celsius
    public static double toCelsiusTransform(UnityTemp type, double temp) {
        if (type == UnityTemp.FAHRENHEIT) {
            return (temp - 32) / 1.8;
        } else if (type == UnityTemp.KELVIN) {
            return temp - 273.15;
        } else {
            return temp;
        }
    }

    //retorna temperatura em kelvin
    public static double toKelvinTransform(UnityTemp type, double temp) {
        if (type == UnityTemp.FAHRENHEIT) {
            return (temp - 32) *5/9+273.15;
        } else if (type == UnityTemp.CELSIUS) {
            return temp + 273.15;
        } else {
            return temp;
        }
    }
}