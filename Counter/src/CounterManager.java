import java.io.*;
import java.text.MessageFormat;

public class CounterManager {
    public void saveCounter(CounterModel counter)
    {
        try {
            FileOutputStream outputStream = new FileOutputStream("counterSave");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(counter);
            objectOutputStream.close();
        }
        catch (Exception ex) {
            System.out.println(MessageFormat.format("Не удалось сохранить данные, ошибка=[{0}]", ex));
        }
    }
    public CounterModel loadCounter()
    {
        try
        {
            FileInputStream fileInputStream = new FileInputStream("counterSave");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            CounterModel counter = (CounterModel) objectInputStream.readObject();

            return counter;
        }
        catch (Exception ex)
        {
            return null;
        }
    }
}
