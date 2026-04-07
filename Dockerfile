# Estágio 1: Build (Compilação)
# Usamos uma imagem que já vem com Maven e Java 21 instalados
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Define o diretório de trabalho
WORKDIR /app

# Passo de otimização: Copia apenas o pom.xml e baixa as dependências
# Isso acelera os próximos builds se você não mudar as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Agora copia o código fonte e gera o jar
COPY src ./src
RUN mvn clean package -DskipTests

# Estágio 2: Runtime (Execução)
# Usamos apenas o JRE (Java Runtime Environment) que é muito menor que o JDK
FROM eclipse-temurin:21-jre-alpine

# Cria um diretório para a aplicação
WORKDIR /app

# Expõe a porta que você definiu
EXPOSE 8097

# Copia o JAR gerado no estágio de build para esta imagem limpa
# O asterisco ajuda se a versão mudar, mas garante que pegamos o jar final
COPY --from=build /app/target/*.jar app.jar

# Define o fuso horário (opcional, mas recomendado)
ENV TZ=America/Sao_Paulo

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]