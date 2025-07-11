javac -d bin -cp "lib/mssql-jdbc-12.10.1.jre11.jar" src/**/*.java 
# -d bin: chỉ định thư mục chứa các file class



java -cp "bin:lib/mssql-jdbc-12.10.1.jre11.jar" App
# -cp "bin:lib/mssql-jdbc-12.10.1.jre11.jar" App là chỉ định classpath, bao gồm thư mục bin và thư viện JDBC
#App đóng vai trò là điểm vào của ứng dụng Java, giống như hàm main trong c++ 

