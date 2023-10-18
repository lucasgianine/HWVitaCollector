# ![HWVitaCollector](https://github.com/vita-sptech/HWVitaCollector/assets/125743142/7b4d0ba7-f0df-4dfc-a028-9502548a16b7)
O <b>HWVitaCollector</b> é um software desenvolvido e distribuído por nós da Vita Health, esse software é um executável .JAR que é instalado nos computadores que ficam em ambientes hospitalares, que utiliza do seu sistema operacional para fazer o monitoramento dos dados da sua máquina.

## Passo a Passo da Instalação
Não precisa se preocupar que os passos informados aqui, já ocorrerão automaticamente assim que você inicializar o arquivo `installer.sh` (<a href="./installer.sh">Shell Script</a>), esses passos da instalação é apenas um guia mostrando como é o funcionamento do script e de como você deve seguir.
- #### Informe `Y` para prosseguir com a instalação do software
- #### Será verificado se você tem qualquer instalação do Java instalado em sua máquina
    - #### a. Se tiver o Java instalado, será verificado se sua versão é igual a `17.0.6` e `17.0.8`
    - #### b. Se a versão for menor que `17.x`, informe `Y` para atualizar seu JDK para uma versão mais atual
    - #### c. Se não existir nenhuma versão do Java instalado, informe `Y` para <b>atualizar</b> os pacotes e <b>instalar</b> o Java em sua máquina
- #### Tendo o JDK `17.x` instalado, será instalado o arquivo `.jar` através do código `curl -LJO URL_DO_GITHUB`
- #### Assim que for instalado, será verificado se contém o arquivo `HWVitaCollector.jar`
    - #### Se existir o arquivo `.jar`, o mesmo será executado
- #### Entre na sua conta cadastrada para inicializar o sistema na sua máquina (Se não possui uma conta, crie <a href="https://github.com/vita-sptech/idleCare-website">aqui</a>)
![telaLogin](https://github.com/vita-sptech/HWVitaCollector/assets/125743142/41a6c0e8-5e6a-4205-8052-a73b1bb69dfd)

## Fluxo do sistema
![fluxogramaSoftware](https://github.com/vita-sptech/HWVitaCollector/assets/125743142/f0de6fb7-f64d-483d-9d3f-21bad8ce3654)

## Conheça a Vita
A Vita Health é uma empresa (fictícia) que foi fundada por um grupo de estudantes da faculdade SPTech, focada em entender as necessidades tecnológicas na área da saúde do Brasil criando soluções efetivas nessa área tão delicada.
<a href="https://github.com/vita-sptech">Saiba mais!</a>

## 💻 Desenvolvedores
‣ 👨‍🚀 [David Moraes](https://github.com/Davidnmsilva)
‣ 👨‍🚀 [Jéssica Barreiros](https://github.com/jessicabarreirosm)
‣ 👨‍🚀 [Keven Nascimento](https://github.com/kevenhistolino)
‣ 👨‍🚀 [Leonardo Bento](https://github.com/leopls07)
‣ 👨‍🚀 [Lucas Santos](https://github.com/lucasgianine)
‣ 👨‍🚀 [Vitor Lopes](https://github.com/VitorLpsDias)