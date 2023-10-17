#!/bin/bash

# Incializando instalação do software
echo "==========================================================="
echo "Sejá bem-vindo(a) ao instalador do software HWVitaCollector"
echo "==========================================================="

echo "Você deseja instalar nosso software? [Y/n]"
read getRes

if [ "$getRes" = "Y" ]
	then	
	# Verificando se o Java 17.x está instalado
	java -version

	if [ $? -eq 0 ];
		then
		# Guardando a versão atual do usuário em uma variável
		versao=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')

		if [[ "$versao" == "17.0.6" ]]
			then
			echo "Java $versao já está instalado."
			echo "Prosseguindo com a instalação do software..."
		else
			echo "Java $versao está instalado, mas é inferior a versão necessária."
			echo "Você deseja instalar a versão atual? [Y/n]"
			read res

			if [ "$res" = "Y" ]
				then
				echo "Inicializando a instalação..."
				# Atualizando os pacotes do sistema
				sudo apt update
				# Atualizando a versão do Java para a versão necessário
				sudo apt install openjdk-17-jdk -y
                nova_versao=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')
				echo "Java $nova_versao instalado com sucesso!"
			else
				echo "O Java não será instalado."
				echo "Finalizando execução..."
				exit 1 # Finalizando o processo e printando status de erro para o usuário
			fi
		fi
	else
		echo "Para prosseguir com a nossa instalação, você precisa ter o Java instalado."
		echo "Deseja instalar o Java? [Y/n]"
		read res

		if [ "$res" = "Y" ]
			then
			echo "Inicializando a instalação..."
			# Atualizando os pacotes do sistema
			sudo apt-get update && sudo apt-get upgrade -y
			# Instalando o Java na versão 17
			sudo apt install openjdk-17-jdk -y
            nova_versao=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')
			echo "Java $nova_versao instalado com sucesso!"
		else
			echo  "O Java não será instalado."
			echo "Finalizando execução..."
			exit 1 # Finalizando o processo e printando status de erro para o usuário
		fi
	fi

	# Inicializando a instalação do .jar no repositório da Vita (Projeto de PI)
	curl -o HWVitaCollector.jar -LJO https://github.com/vita-sptech/HWVitaCollector/tree/main/HWVitaCollector/out/artifacts/HWVitaCollector_jar/HWVitaCollector.jar

	if [ $? -eq 0 ]
		then
		# Executando o .jar
		java -jar HWVitaCollector.jar
	else
		echo "Erro ao rodar o .jar"
		exit 1
	fi
else
    echo "O software não será instalado."
    echo "Finalizando execução..."
    exit 1 # Finalizando o processo e printando status de erro para o usuário
fi