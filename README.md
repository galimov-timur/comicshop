# comicshop
**Comicshop** - учебный проект. Web приложение интернет магазина по продаже комиксов.

### **Требования:**
 - apache tomcat 9.0.36
 - openjdk version "11.0.9.1"
 - PostgreSQL 10.15
 - Ubuntu 18.04.5 LTS

### **Настройка и запуск приложения:**
###### Настройка базы данных
После установки postgreSQL 10, используя утилиту командной строки psql войти под 
пользователем имеющим привелегии на создание и удаление баз данных. Для этого нужно
открыть терминал и выполнить следующую команду:

`psql -h 127.0.0.1 -p 5432 -U postgres`

где -h адрес хоста, -p порт используемый базой данных, -U пользователь

Для заполнения базы данных товарами и категориями нужно запустить скрипт из папки 
database database.sql. 

`postgres=# \i path` 

где вместо path, указать путь к папке со скриптом в вашей системе, например:

`postgres=# \i /home/timur/Desktop/EE/project/comicshop/database/database.sql`

*Примечание: В вашей операционной системе должна быть доступна локаль ru_RU.utf8

Чтобы приложение получило доступ к созданной базе данных, нужно указать имя пользователя
и пароль, каторые были использованы при создании базы данных в файле context.xml,
который расположен в src/main/webapp/META-INF.

`<Resource name="jdbc/comicshop"
        username="user"
        password="password"
 />`

###### Настройка внешних ресурсов

Для хранения изображений товаров в приложении используется локальная директория.
Для ее настройки нужно найти файл server.xml, расположенный в директории conf установленного сервера Apache Tomcat.
Например для linux:
`/opt/tomcat/apache-tomcat-9.0.36/conf/server.xml`

И добавить внутри тэга HOST следующую строку:
`<Context docBase="/path/to/images/" path="/catalog"/>`

где docBase - путь к ресурсу с изображениями товаров в вашей системе,
    path - путь по которому данный ресурс будет доступен в приложении

Для того, чтобы при добавлении товара через панель администратора изображения копировались в 
нужную папку, путь к ней, также нужно указать в файле web.xml.

`<context-param>
    <param-name>imageSource</param-name>
    <param-value>/path/to/images/</param-value>
</context-param>`

Для отображения изображений скопируйте файлы из папки database/images в настроенную папку.

###### Запуск

Для запуска приложения, нужно скомпилировать с помощью maven архив war. И скопировать его в 
папку webapps, которая расположена в директории с установленным Tomcat сервером.
После этого запустить сервер. Приложение будет доступно по адресу http://localhost/comicshop

