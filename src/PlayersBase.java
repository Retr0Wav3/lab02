import java.awt.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PlayersBase
{
    private JFrame PlayersList;
    private DefaultTableModel model;
    private HashMap<String, String> iconsPaths;

    private JToolBar toolBar;
    private JScrollPane scroll;
    private JTable players;
    private JComboBox filter;
    private JTextField request;
    private JButton search;

    public JButton CreateButton(HashMap<String, String> map, String key)
    {
        String path = null;

            if(map.containsKey(key))
                path = map.get(key);
            else
                throw new NullPointerException("Hashmap doesn't contain such an element");

        return new JButton(new ImageIcon(path));
    }

    public void AddPaths(HashMap<String,String> iconsPaths)
    {
        iconsPaths.put("save", "./icons/save.png");
        iconsPaths.put("open", "./icons/open.png");
        iconsPaths.put("add", "./icons/add.png");
        iconsPaths.put("delete", "./icons/delete.png");
        iconsPaths.put("calendar", "./icons/calendar.png");
        iconsPaths.put("results", "./icons/results.png");
        iconsPaths.put("print", "./icons/print.png");
    }


    public void Show()
    {
        // Создание окна
        PlayersList = new JFrame("Список игроков");
        PlayersList.setSize(500, 300);
        PlayersList.setLocation(100, 100);
        PlayersList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание кнопок и прикрепление иконок
        iconsPaths = new HashMap<>();
        AddPaths(iconsPaths);

        JButton save = CreateButton(iconsPaths, "save");
        JButton open = CreateButton(iconsPaths, "open");
        JButton add = CreateButton(iconsPaths, "add");
        JButton delete = CreateButton(iconsPaths, "delete");
        JButton calendar = CreateButton(iconsPaths, "calendar");
        JButton results = CreateButton(iconsPaths, "results");
        JButton print = CreateButton(iconsPaths, "print");


        // Настройка подсказок для кнопок
        save.setToolTipText("Сохранить внесённые изменения");
        open.setToolTipText("Открыть список игроков");
        add.setToolTipText("Добавить информацию");
        delete.setToolTipText("Удалить информацию");
        calendar.setToolTipText("Открыть календарь игр");
        results.setToolTipText("ПОсмотреть отчёт о результативности");
        print.setToolTipText("Напечатать отчёт");

        // Добавление кнопок на панель инструментов
        toolBar = new JToolBar("Панель инструментов");
        toolBar.add(save);
        toolBar.add(open);
        toolBar.add(add);
        toolBar.add(delete);
        toolBar.add(calendar);
        toolBar.add(results);
        toolBar.add(print);

        // Размещение панели инструментов
        PlayersList.setLayout(new BorderLayout());
        PlayersList.add(toolBar, BorderLayout.NORTH);

        // Создание таблицы с данными
        String [] columns = {"Игрок", "Специализация", "Провёл игр", "Рейтинг"};

        String [][] data = {{"Игрок Х.Х.", "Нападающий", "16", "7.82"}};

        model= new DefaultTableModel(data, columns);
        players = new JTable(model);
        players.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scroll = new JScrollPane(players);

        // Размещение таблицы с данными
        PlayersList.add(scroll, BorderLayout.CENTER);


        // Подготовка компонентов поиска
        filter = new JComboBox(new String[]{"Игрок", "Специализация", "Провёл игр", "Рейтинг"});
        request = new JTextField("Введите запрос");
        search = new JButton("Поиск");

        // Добавление компонентов на панель
        JPanel filterPanel = new JPanel();
        filterPanel.add(filter);
        filterPanel.add(request);
        filterPanel.add(search);

        // Размещение панели поиска внизу окна
        PlayersList.add(filterPanel, BorderLayout.SOUTH);

        // Визуализация экранной формы
        PlayersList.setVisible(true);
    }
}
