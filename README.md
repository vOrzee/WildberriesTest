#Задача

Нужно создать мобильное приложение “Пора в путешествие” с двумя экранами.

##Первый экран
Первый экран представляет из себя список актуальных дешевых авиаперелетов.

Каждая ячейка списка должна содержать:

    Город отправления.
    Город прибытия.
    Дата отправления.
    Дата возвращения.
    Цена в рублях.
    Иконка “Лайк” (имеет два состояния и означает лайкнул ли пользователь данный авиаперелет).

##Второй экран
Второй экран - это детализация перелета с кнопкой “Лайк”.
Второй экран открывается при выборе одной из ячеек первого экрана. Должна быть возможность вернуться к первому экрану назад.

Второй экран содержит данные по выбранному перелету (город отправления, город прибытия, дата отправления, дата возвращения, цена в рублях) и кнопку “Лайк”.

Кнопка лайк имеет два состояния:

    Перелет нравится.
    Перелет не нравится.

Примечания

    Дизайн произвольный, на усмотрение разработчика.
    Список авиаперелетов запрашивается один раз при старте приложения.
    Пока происходит загрузка списка должен быть отображен индикатор загрузки.
    Если пользователь лайкнул перелет и вернулся в список перелетов, то в списке у “лайкнутого” перелета должна “гореть” иконка “Лайк”.
    Если пользователь “лайкнул” перелет, вышел на первый экран и еще раз зашел на второй экран, то кнопка “Лайк” должна быть в состоянии “Перелет нравится” и наоборот.
    Апи для списка авиаперелетов:
    curl ‘vmeste.wildberries.ru...e/GetCheap’
    -H ‘accept: application/json, text/plain, /’
    -H ‘content-type: application/json’
    –data-raw ‘{“startLocationCode”:“LED”}’
    –compressed

Сколько времени потребуется на выполнение задания:
4–5 часов.