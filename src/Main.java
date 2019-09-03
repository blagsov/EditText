import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
 * code written
 * by Zoya Klocheva
 */
public class Main {
    public static void main(String[] args) throws IOException {
        /*
         *Приложение на работу с файлами, форматирование текста (задача переписать файл, чтобы выглядел аккуратно),
         * регулярные выражения, reader, writer...
         */

        //пути к файлам, txt2 перед работой можно удалить - будет создан новый, либо оставить -будет перезаписан
        String text1 = "txt1.txt";
        String text2 = "txt2.txt";

        //экземпляры reader и writer для чтения и записи, с указанием путей
        FileReader fr = new FileReader(text1);
        FileWriter fw = new FileWriter(text2);
        //Создание списка строк ("линий") с чтением всех строк из первого файла
        List<String> lines = Files.readAllLines(Paths.get(text1), Charset.forName("windows-1251"));
        //цикл, чтобы можно было перебрать все строки
        for (String line : lines) {
            //создание новой строки с использованием разделителя строк ("линия")
            String newLine = line + System.lineSeparator();
            //массив строк, где краткие строки делятся по табуляции
            String[] strings = newLine.split("\\t+");

            //цикл, где перебираются коротенькие строки - по стобцам
            for (String string : strings) {
                //новая строка, где к "коротким строкам" - столбцам - добавляются оступы, табуляция, выравнимание и символ "|"
                String newString = String.format("%-12s", "\t|\t" + string);
                //на случай, если данных нет и столбец пустой, чтобы не было сдвига
                if (string.equals("")) {
                    newString = "\t\t";
                }
                //делается паттерн для обработки первого столбца
                Pattern pattern = Pattern.compile("\\d{2}\\D{1}\\d{2}\\D{1}\\d{4}");
                Matcher matcher = pattern.matcher(string);//при нахождении соответствия среди "маленьких строк"
                while (matcher.find()) {
                    newString = string;//строка не меняется
                }
                //записываются данные
                fw.write(newString);
            }
        }
        //закрывается reader и writer
        fw.flush();
        fr.close();
        fw.close();
    }
}

