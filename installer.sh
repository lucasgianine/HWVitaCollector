#!/bin/bash

# Incializando instalação do software
echo "==========================================================="
echo "Sejá bem-vindo(a) ao instalador do software HWVitaCollector"
echo "==========================================================="

echo "Você deseja instalar nosso software? [Y/n]"
read getRes

if [ "$getRes" = "Y" ] || [ "$getRes" = "y" ]
	then	
	java -version # Verificando a versão do Java

	if [ $? -eq 0 ]
        then
        versao_atual=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')

        if [[ "$versao_atual" == "17.0.6" ]] || [[ "$versao_atual" == "17.0.8" ]]
            then
            echo "Java $versao_atual já está instalado."
			echo "Prosseguindo com a instalação do software..."
        else 
			echo "Java está instalado, mas é inferior a versão necessária."
		    echo "Você deseja instalar a versão atual? [Y/n]"
		    read res

		    if [ "$res" = "Y" ] || [ "$res" = "y" ]
				then
			    echo "Inicializando a instalação..." # Instalando o Java na versão 17.x
			    sudo apt-get update # Atualizando os pacotes do sistema
    			sudo apt install openjdk-17-jdk -y
                nova_versao=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}') # Pegando a nova versão do Java instalado
				echo "Java $nova_versao instalado com sucesso!"
		    else
			    echo "O Java não será instalado."
    			echo "Finalizando execução..."
                sleep 2 # Timeout de espera antes de finalizar o processo
				exit 1 # Finalizando o processo e printando status de erro para o usuário
		    fi
        fi
	else
		echo "Para prosseguir com a nossa instalação, você precisa ter o Java instalado."
		echo "Deseja instalar o Java? [Y/n]"
		read res

		if [ "$res" = "Y" ] || [ "$res" = "y" ]
			then
			echo "Inicializando a instalação..."
			sudo apt-get update
			sudo apt install openjdk-17-jdk -y
            nova_versao=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')
			echo "Java $nova_versao instalado com sucesso!"
		else
			echo  "O Java não será instalado."
			echo "Finalizando execução..."
            sleep 2
			exit 1
		fi
	fi

	# Inicializando a instalação do .jar no repositório da Vita (Projeto de PI)
	curl -LJO https://github.com/vita-sptech/HWVitaCollector/raw/main/HWVitaCollector/out/artifacts/HWVitaCollector_jar/HWVitaCollector.jar
    

	if [ $? -eq 0 ]
        then
        if [ -f HWVitaCollector.jar ] # Verificando se o arquivo baixado é um arquivo .jar válido
            then
            echo "Iniciando o software..."
		    java -jar HWVitaCollector.jar # Executando o arquivo .jar
        else
            echo "Erro ao rodar o .jar"
            sleep 2
		    exit 1
        fi
	else
		echo "Erro ao executar o curl"
        sleep 2
		exit 1
	fi
else
    echo "O software não será instalado."
    echo "Finalizando execução..."
    sleep 2
    exit 1
fi