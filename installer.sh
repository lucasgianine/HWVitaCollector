#!/bin/bash

# Incializando instalação do software
echo "+-------------------------------------------------------------+"
echo "| Sejá bem-vindo(a) ao instalador do software HWVitaCollector |"
echo "+-------------------------------------------------------------+"

echo "[Etapa de verificação]"
echo "Você deseja instalar nosso software? [Y/n]"
read getRes

if [ "$getRes" = "Y" ] || [ "$getRes" = "y" ]
then
	echo -e "\n[Atenção]"
	echo "Antes de iniciarlizamos a instalação do software, precisamos atualizar os pacotes de sistema."

	echo "Atualizando pacotes..."
	sudo apt-get update && sudo apt-get upgrade -y # Atualizando os pacotes do sistema
	sleep 2
	clear

	echo "[Atualização concluída]"
	echo "Pacotes de sistema atualizados!"
	sleep 3
	clear

	echo "Iniciando etapa de verificação..."
	echo -e "[Etapa de verificação (1/2) - Java]\n"

	java -version # Verificando a versão do Java

	if [ $? -eq 0 ]
  then
    versao_atual=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')

    if [[ "$versao_atual" == "17.0.6" ]] || [[ "$versao_atual" == "17.0.8" ]]
    then
			echo -e "\n[Etapa de verificação do Java concluída]"
      echo "Java $versao_atual já está instalado."
			echo "Prosseguindo com a próxima etapa..."

			sleep 5
			clear
    else
			echo "[Atenção]"
			echo "Java está instalado, mas é necessário baixar uma versão específica."
		  echo "Você deseja instalar a versão atual? [Y/n]"
		  read res

	    if [ "$res" = "Y" ] || [ "$res" = "y" ]
			then
			  echo -e "Inicializando a instalação...\n" # Instalando o Java na versão 17.x

    		sudo apt install openjdk-17-jdk -y

        nova_versao=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}') # Pegando a nova versão do Java instalado
				echo -e "\n[Instalação concluída]"
				echo "Java $nova_versao instalado com sucesso!"

				sleep 5
				clear
		  else
				echo "[Etapa cancelada]"
			  echo "Ok, entendido. O Java não será instalado."
    		echo "Finalizando execução..."

        sleep 5 # Timeout de espera antes de finalizar o processo
				exit 1 # Finalizando o processo e printando status de erro para o usuário
		  fi
    fi
	else
		echo "[Atenção]"
		echo "Para prosseguir com a nossa instalação, você precisa ter o Java instalado."
		echo "Deseja instalar o Java? [Y/n]"
		read res

		if [ "$res" = "Y" ] || [ "$res" = "y" ]
		then
			echo -e "Inicializando a instalação...\n"

			sudo apt-get update
			sudo apt install openjdk-17-jdk -y

			echo -e "\n[Instalação concluída]"
			echo "Nova versão do Java instalado com sucesso!"

			sleep 5
			clear
		else
			echo "[Etapa cancelada]"
			echo "Ok, entendido. O Java não será instalado."
    	echo "Finalizando execução..."

      sleep 5
			exit 1
		fi
	fi

	echo -e "[Etapa de verificação (2/2) - Docker]\n"

	docker --version # Verificando se o Docker está instalado

	if [ $? -eq 0 ]
	then
		echo -e "\n[Etapa de verificação do Docker concluída]"
    echo "O Docker já está instalado na sua máquina."

		sudo systemctl start docker
		sudo systemctl enable docker
		sleep 2

		echo "Verificando banco de dados do software..."

		sleep 5
		clear
	else
		echo -e "\n[Atenção]"
		echo "Para prosseguir com a nossa instalação, você precisa ter o Docker instalado."
		echo "Deseja instalar o Docker? [Y/n]"
		read res

		if [ "$res" = "Y" ] || [ "$res" = "y" ]
		then
			echo -e "Inicializando a instalação...\n"
			sleep 2
			sudo apt-get update
			sleep 2
			sudo apt install docker.io -y
			sleep 2
			sudo systemctl start docker
			sleep 2
			sudo systemctl enable docker
			sleep 5
			clear

			echo "[Instalação concluída]"
			echo "Docker foi instalado com sucesso!"

			sleep 5
			clear
		else
			echo "[Etapa cancelada]"
			echo "Ok, entendido. O Docker não será instalado."
    	echo "Finalizando execução..."

      sleep 5
			exit 1
		fi
	fi

	echo -e "\n[Etapa de criação]"
	echo "Prosseguindo com a criação do banco de dados local..."
	echo -e "\nCriando banco de dados...\n"

	
	sudo docker run -d -p 3306:3306 --name VitaContainer vitahealth/db:latest
  	
	
	sleep 2
	echo -e "\n[Banco de dados criado com sucesso]"
	sleep 5
	clear

	echo "[Etapa de instalação]"
	echo -e "Prosseguindo com a instalação do software...\n"

	# Inicializando a instalação do .jar no repositório da Vita (Projeto de PI)
	curl -LJO https://github.com/vita-sptech/HWVitaCollector/raw/main/HWVitaCollector/out/artifacts/HWVitaCollector_jar/HWVitaCollector.jar

	if [ $? -eq 0 ]
  then
    if [ -f HWVitaCollector.jar ] # Verificando se o arquivo baixado é um arquivo .jar válido
      then
      echo -e "\nIniciando o sistema...\n"

		 chmod +x HWVitaCollector.jar # Dando permissão de execução para o arquivo .jar
   		 sleep 15
		 sudo java -jar HWVitaCollector.jar # Executando o arquivo .jar

			sleep 5
			clear
  	else
			echo -e "\n[Erro]"
    	echo "Não foi possível iniciar o sistema."

      sleep 5
	    exit 1
    fi
	else
		echo -e "\n[Erro]"
		echo "Não foi possível fazer a instalação do software."

    sleep 5
		exit 1
	fi
else
	echo -e "\n[Instalação cancelada]"
  echo "O software não será instalado."
  echo "Finalizando execução..."

  sleep 5
  exit 1
fi
