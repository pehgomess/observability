*Javabook Dynatrace*

# Chapter: Application Performance Concepts



Application performance é uma área de importância crescente. Estamos construindo aplicações cada vez maiores. A funcionalidade dos aplicativos de hoje está ficando cada vez mais poderosa. Ao mesmo tempo, usamos arquiteturas de grande escala altamente distribuídas que também integram serviços externos como peças centrais do cenário de aplicativos. Para otimizar esses aplicativos, precisamos de um conhecimento profundo de como medir e otimizar o desempenho dos aplicativos corporativos. Antes de nos aprofundarmos nos detalhes técnicos de como otimizar o desempenho, examinamos os conceitos subjacentes importantes.

Um conhecimento sólido do tópico ajuda a gerenciar o performance de um aplicativo com mais eficiência. Isso ajudará a fazer com que o gerenciamento de performance pareça menos complexo. Conforme você entende o histórico técnico, muitas coisas ficam repentinamente muito mais simples e você também verá aumentar sua eficiência na solução de problemas de desempenho.

Enquanto discutimos os princípios fundamentais do gerenciamento de performance, os leitores experientes apreciarão uma discussão detalhada de conceitos importantes de performance. Especialmente uma compreensão sólida de diferentes técnicas de medição e suas vantagens e desvantagens, bem como sobrecarga (overhead) de medição (measurement) e precisão de medição é a chave na interpretação dos resultados da medição. Uma compreensão profunda das técnicas de coleta de dados e representação de dados também nos ajuda a selecionar as ferramentas adequadas para nossas tarefas de análise.



## Differentiating (Diferenciando) Performance from Scalability

Ao longo deste livro, discutiremos desempenho e escalabilidade: como medi-los, identificar problemas e, em seguida, otimizar. No entanto, muitas pessoas usam esses termos como sinônimos. É provável que as pessoas digam que "o desempenho do aplicativo é ruim", mas isso significa que os tempos de resposta são muito altos ou que o aplicativo não pode ser escalado para mais do que um grande número de usuários simultâneos? Os sintomas podem ser os mesmos, mas esses são dois problemas bem diferentes que descrevem características diferentes de um aplicativo.

Ao discutir o desempenho, devemos diferenciar cuidadosamente esses dois pontos. Nem sempre é uma tarefa fácil, porque ao mesmo tempo, estão inter-relacionados e um pode, e muitas vezes afeta, o outro.

### Defining and Measuring (medindo) Performance

O estado do aplicativo determina a maneira como as solicitações são processadas. A carga atual, a complexidade da solicitação e outros fatores de aplicativo e sistema (como uso de CPU ou rede) afetam a capacidade de resposta do aplicativo. São as características de resposta que definem o desempenho do aplicativo. Mais especificamente, existem três medidas básicas de desempenho:

- Response Time: Esta é a métrica de desempenho mais amplamente usada e é simplesmente uma medida direta de quanto tempo leva para processar uma solicitação.

- Throughput: Uma contagem direta do número de solicitações que o aplicativo pode processar em um intervalo de tempo definido. Para aplicativos da Web, uma contagem de impressões de página ou solicitações por segundo é freqüentemente usada como uma medida de taxa de transferência.

- System availability:  Geralmente expresso como uma porcentagem do tempo de execução do aplicativo menos o tempo que o aplicativo não pode ser acessado pelos usuários. Essa é uma métrica indispensável, porque tanto o tempo de resposta quanto a taxa de transferência são zero quando o sistema está indisponível.

O desempenho também pode ser definido por solicitações de recursos e medindo as solicitações de recursos em relação à taxa de transferência. Isso se torna uma métrica chave no contexto do planejamento de recursos. Por exemplo, você pode precisar saber quais recursos são necessários para atingir o dimensionamento total do aplicativo, que é uma questão de saber a frequência das solicitações para cada tipo de recurso.

O consumo de recursos está se tornando ainda mais importante para aplicativos implantados em ambientes "elásticos", como a nuvem. Com esses aplicativos de maior escala e dispersos geograficamente, a escalabilidade e o desempenho tornam-se inextricavelmente vinculados, o que pode ser facilmente descrito de um ponto de vista centrado em recursos.

Lembre-se de que mesmo os aplicativos mais amplamente fornecidos e equipados possuem recursos limitados. Ao mesmo tempo, o aumento da carga do sistema causará demandas desiguais em todos os recursos necessários. Isso se torna a medida definitiva de desempenho do mundo real. 

Quanto mais solicitações os usuários enviarem ao aplicativo, maior será a carga. Os resultados podem ser plotados tão simples quanto a mecânica básica. Tanto quanto o atrito ou a gravidade acabarão por trazer um corpo em movimento para descansar, conforme a carga aumenta, o desempenho diminui.

![Increasing load affects the response time and throughput of an application.](https://dt-cdn.net/images/loadandperformance-510-7905e160b8.png)

Figura 1.1: O aumento da carga afeta o tempo de resposta e o rendimento de um aplicativo

Da mesma forma, o desempenho do aplicativo sempre será afetado pelas restrições de recursos. Seus usuários podem experimentar essas restrições como instabilidade do aplicativo, mas a causa subjacente pode ser qualquer uma das métricas que definem o desempenho do aplicativo.

Portanto, é de vital importância descrever o desempenho como uma proporção de alguma quantidade mensurável - resposta, taxa de transferência, disponibilidade ou solicitações - ao longo do tempo. Alguém fica facilmente tentado a usar uma espécie de abreviação de "caixa preta", como

*Response time is 2 seconds.*

Não faça isso! Sem um contexto descritivo apropriado, esse tipo de declaração não tem sentido e, em última análise, é inútil. Em vez disso, seja prolixo:

*O tempo de resposta do sistema é de 2 segundos para 500 solicitações simultâneas, com uma carga de CPU de 50% e uma utilização de memória de 92%.*

Discutiremos ferramentas para medir o desempenho, bem como métodos para lidar com esses problemas de desempenho nos próximos capítulos.

### How to Enable Application Scalability

A capacidade de superar os limites de desempenho adicionando recursos é definida como escalabilidade. Não importa quanto hardware tenhamos em um determinado ponto, veremos desempenho decrescente. Isso significa aumentar os tempos de resposta ou um limite na taxa de transferência. Adicionar hardware adicional resolverá o problema? Se sim, então podemos escalar. Caso contrário, temos um problema de escalabilidade.

Dependendo das mudanças na topologia do aplicativo, diferenciamos as duas abordagens de escalabilidade a seguir:

Escala vertical ou scale-up de um único nó: Adicionando recursos de hardware a um nó existente. Isso geralmente é usado em ambientes virtualizados onde é possível expandir os recursos do sistema dinamicamente. Ele tem a vantagem clara de não exigir alterações na arquitetura do aplicativo. Ao mesmo tempo, o nível de escalabilidade sempre será restrito por hardware.

![Vertical scaling by adding hardware resources on to existing servers](https://dt-cdn.net/images/verticalscaling-152-26b6946abe.png)

Figura 1.2: Escala vertical adicionando recursos de hardware aos servidores existentes

Escala horizontal ou escalonamento adicionando nós: Despachar solicitações entre nós adicionais é a solução de escalonamento preferida quando é mais fácil adicionar nós adicionais do que aumentar os recursos de um único nó. Isso é especialmente verdadeiro em ambientes baseados em nuvem (veja a figura abaixo). Eventualmente, temos que escalar horizontalmente de qualquer maneira, pois uma única máquina sempre tem capacidade limitada. Uma vantagem adicional é que os nós baratos fornecem maior capacidade de failover e melhor disponibilidade de aplicativos. A desvantagem óbvia é que os clusters devem ser projetados para que os nós possam ser adicionados e removidos com facilidade.

![Horizontal scaling by adding new servers, scaling out](https://dt-cdn.net/images/horizontalscaling-510-f596df2477.png)

### Will Scaling Solve Our Performance Problems?

O dimensionamento de um aplicativo adicionando recursos é o caso ideal, mas em algum momento, isso se tornará muito caro. Devemos considerar adicionar hardware, dimensionar ou mudar nossa arquitetura.

No entanto, freqüentemente o problema de escalabilidade não está relacionado ao hardware e adicionar hardware não ajudará. Se observarmos que os recursos não estão sobrecarregados, isso geralmente indica um problema relacionado à sincronização, que geralmente está relacionado ao acesso serializado a dados compartilhados em vez de um problema de desempenho. Adicionar recursos não ajudará porque não é aí que está o gargalo.

Vejamos o exemplo de atualização de um inventário. Se quisermos mantê-lo consistente em todos os momentos, apenas um processo pode atualizá-lo a qualquer momento. A duração desse processamento indica onde estão nossos limites de escalabilidade. Se o processamento levar 200 milissegundos, o limite de escalabilidade de nosso aplicativo é de cinco solicitações por segundo - independentemente do hardware disponível.

Se quisermos obter um throughput mais alto, temos que melhorar a performance em geral em vez de melhorar a escalabilidade. Isso pode significar que decidimos abrir mão da consistência. Colocaríamos nossas mudanças de estoque em uma fila que seriam atualizadas posteriormente. Isso é chamado de "eventual consistency". Com essa abordagem, no entanto, não é mais possível verificar se uma determinada quantidade de um item está atualmente em estoque.

## Calculating Performance Data

Como este exemplo mostra, realmente precisamos de boas medidas de desempenho para nos orientar na tomada de decisões de escalabilidade.

A análise de desempenho é sempre baseada em números. Em muitos casos, não estamos trabalhando com "valores brutos", mas sim com dados agregados. Se cada medição fosse vista individualmente, você não conseguiria ver a floresta por causa das árvores. Ao mesmo tempo, certas abordagens de medição, como JMX (que discutiremos mais tarde), apenas nos fornecem dados agregados.

### Working with Averages

A média é a representação de dados mais básica e também mais amplamente usada e é incorporada a todas as ferramentas de desempenho. É provavelmente a medida mais usada em demasia, porque só pode fornecer uma "primeira impressão" do desempenho. Por exemplo, a média de uma série de medições voláteis, onde alguns valores são muito baixos e outros muito altos, pode ser facilmente distorcida por outliers (Os *outliers* são dados que se diferenciam drasticamente de todos os outros, são pontos fora da curva normal). Portanto, embora a média possa parecer boa, pode falhar em revelar um problema real de desempenho.

O intervalo de agregação pode distorcer as coisas ainda mais. Quando os dados de medição estão sujeitos a flutuações de tempo em durações mais longas, a média não pode refletir essas informações e perde seu significado. Da mesma forma, em um período muito curto em que apenas um pequeno número de medições é feito, a média é simplesmente imprecisa estatisticamente.

Por exemplo, se examinarmos o tempo de resposta de um aplicativo em um período de 24 horas, os valores de pico serão ocultados pela média. As medições feitas quando o sistema estava sob carga baixa, com bons tempos de resposta, farão uma "média" dos tempos de pico de carga do sistema.

### Interpreting Minimum and Maximum Values

Os valores mínimo e máximo nos dão uma medida dos extremos e da extensão desses extremos. Como sempre há outliers em qualquer medição, eles não são necessariamente muito significativos. Na prática, esses dados raramente são usados, pois não fornecem uma base para determinar a frequência com que o valor correspondente realmente ocorreu. Pode ter ocorrido uma ou cem vezes.

O principal uso desses valores é verificar o quão alta é a qualidade do valor médio calculado. Se esses valores forem muito próximos, pode-se presumir que a média é representativa dos dados.

Em aplicativos com requisitos de desempenho exigentes, o máximo ainda é frequentemente usado em vez de, ou além da média, para verificar se os tempos de resposta estão abaixo de um determinado nível. Isso é especialmente verdadeiro para aplicativos que nunca devem exceder certos limites.

### Replacing the Average with the Median

A mediana ou valor médio de uma série de números é outra representação amplamente usada de dados de desempenho. Também é chamado de 50º percentil; Em uma série de 89 medições, a mediana seria a 45ª medição. A vantagem da mediana é que ela está mais próxima dos dados do mundo real e não é um valor calculado artificialmente como a média, que é fortemente influenciada por outliers. O impacto de outliers na mediana é muito menor do que na média.

### Using the Average in Combination with Standard Deviation

Tomada isoladamente, a média tem significância limitada. No entanto, é consideravelmente mais significativo quando usado com desvio padrão. O desvio padrão mede a dispersão dos valores reais. Quanto maior o desvio padrão, maior é a diferença nos dados de medição. Supondo que nossos valores sejam distribuídos normalmente, pelo menos dois terços de todos os valores ficarão dentro da faixa coberta pela média mais / menos um desvio padrão.

Ao trabalhar com o desvio padrão, presumimos que os dados são normalmente distribuídos. Se nossos dados não seguem essa distribuição, o desvio padrão não faz sentido. Isso significa que temos que entender as características dos dados subjacentes antes de confiarmos em métricas derivadas estatisticamente.

A figura abaixo mostra um exemplo da distribuição do tempo de resposta de um aplicativo. Podemos ver claramente que esses valores não são normalmente distribuídos. Nesse caso, usar a média com desvio padrão nos dará conclusões incorretas e erradas.

![Response-time data showing a non-normal distribution](https://dt-cdn.net/images/responsetimedistribution-510-a42ac33fdf.png)

Figura 1.4: Dados de tempo de resposta não mostrando uma distribuição normal

Existem muitas causas para esse tipo de variação nos dados de medição. Por exemplo, tempos de resposta genuinamente voláteis provavelmente indicam um problema com o aplicativo. Outra possibilidade é que as solicitações individuais diferem no que realmente estão fazendo. Poderíamos também ter combinado pontos de dados em medições únicas que medem coisas diferentes.

Por exemplo, ao examinar os tempos de resposta da página inicial de um portal, nossos dados variam muito, dependendo de quantos portlets estão abertos em cada instância. Para alguns usuários, pode haver apenas um ou dois, enquanto para outros, pode haver vinte ou mais por página. Nesse caso, não é possível tirar conclusões razoáveis sobre os tempos de resposta da página inicial, porque não há uma "página inicial" específica. A única maneira de obter medições significativas neste caso é agregar as medições de forma diferente. Por exemplo, poderíamos agrupar dados de medição pelo número de portlets que são mostrados em uma página.

### Working with Percentiles

Os percentis são provavelmente os mais precisos, mas também os mais difíceis de calcular a representação dos dados. Os percentis definem o valor máximo para uma porcentagem das medidas gerais. Se o 95º percentil para o tempo de resposta do aplicativo for dois segundos, isso significa que 95% de todos os tempos de resposta foram menores ou iguais a dois segundos.

Valores de percentis mais altos, significando do 95º ao 98º, costumam ser usados em vez da média. Isso pode eliminar o impacto de outliers e fornecer uma boa representação dos dados brutos subjacentes.

O conceito de cálculo é muito simples e semelhante à mediana. A diferença é que não apenas dividimos as medidas em 50 por cento, mas de acordo com as faixas de percentis. Portanto, o 25º percentil significa que pegamos os 25% mais baixos de todos os valores medidos.

### Interpreting Minimum and Maximum Values

Os valores mínimo e máximo nos dão uma medida dos extremos e da extensão desses extremos. Como sempre há outliers em qualquer medição, eles não são necessariamente muito significativos. Na prática, esses dados raramente são usados, pois não fornecem uma base para determinar a frequência com que o valor correspondente realmente ocorreu. Pode ter ocorrido uma ou cem vezes.

O principal uso desses valores é verificar o quão alta é a qualidade do valor médio calculado. Se esses valores forem muito próximos, pode-se presumir que a média é representativa dos dados.

Em aplicativos com requisitos de desempenho exigentes, o máximo ainda é frequentemente usado em vez de, ou além da média, para verificar se os tempos de resposta estão abaixo de um determinado nível. Isso é especialmente verdadeiro para aplicativos que nunca devem exceder certos limites.

### Replacing the Average with the Median

A mediana ou valor médio de uma série de números é outra representação amplamente usada de dados de desempenho. Também é chamado de 50º percentil; Em uma série de 89 medições, a mediana seria a 45ª medição. A vantagem da mediana é que ela está mais próxima dos dados do mundo real e não é um valor calculado artificialmente como a média, que é fortemente influenciada por outliers. O impacto de outliers na mediana é muito menor do que na média.

### Using the Average in Combination with Standard Deviation

Tomada isoladamente, a média tem significância limitada. No entanto, é consideravelmente mais significativo quando usado com desvio padrão. O desvio padrão mede a dispersão dos valores reais. Quanto maior o desvio padrão, maior é a diferença nos dados de medição. Supondo que nossos valores sejam distribuídos normalmente, pelo menos dois terços de todos os valores ficarão dentro da faixa coberta pela média mais/menos um desvio padrão.

Ao trabalhar com o desvio padrão, presumimos que os dados são normalmente distribuídos. Se nossos dados não seguem essa distribuição, o desvio padrão não faz sentido. Isso significa que temos que entender as características dos dados subjacentes antes de confiarmos em métricas derivadas estatisticamente.

A figura abaixo mostra um exemplo da distribuição do tempo de resposta de um aplicativo. Podemos ver claramente que esses valores não são normalmente distribuídos. Nesse caso, usar a média com desvio padrão nos dará conclusões incorretas e erradas.

![Response-time data showing a non-normal distribution](https://dt-cdn.net/images/responsetimedistribution-510-a42ac33fdf.png)

Figura 1.4: Dados de tempo de resposta não mostrando uma distribuição normal

Existem muitas causas para esse tipo de variação nos dados de medição. Por exemplo, tempos de resposta genuinamente voláteis provavelmente indicam um problema com o aplicativo. Outra possibilidade é que as solicitações individuais diferem no que realmente estão fazendo. Poderíamos também ter combinado pontos de dados em medições únicas que medem coisas diferentes.

Por exemplo, ao examinar os tempos de resposta da página inicial de um portal, nossos dados variam muito, dependendo de quantos portlets estão abertos em cada instância. Para alguns usuários, pode haver apenas um ou dois, enquanto para outros, pode haver vinte ou mais por página. Nesse caso, não é possível tirar conclusões razoáveis sobre os tempos de resposta da página inicial, porque não há uma "página inicial" específica. A única maneira de obter medições significativas neste caso é agregar as medições de forma diferente. Por exemplo, poderíamos agrupar dados de medição pelo número de portlets que são mostrados em uma página.

### Working with Percentiles

Os percentis são provavelmente os mais precisos, mas também os mais difíceis de calcular a representação dos dados. Os percentis definem o valor máximo para uma porcentagem das medidas gerais. Se o 95º percentil para o tempo de resposta do aplicativo for dois segundos, isso significa que 95% de todos os tempos de resposta foram menores ou iguais a dois segundos.

Valores de percentis mais altos, significando do 95º ao 98º, costumam ser usados em vez da média. Isso pode eliminar o impacto de outliers e fornecer uma boa representação dos dados brutos subjacentes.

O conceito de cálculo é muito simples e semelhante à mediana. A diferença é que não apenas dividimos as medidas em 50 por cento, mas de acordo com as faixas de percentis. Portanto, o 25º percentil significa que pegamos os 25% mais baixos de todos os valores medidos.

A dificuldade está em calcular percentis em tempo real para um grande número de valores: para este cálculo são necessários mais dados em comparação com a média, que requer apenas a soma e o número de valores. Se for possível trabalhar com percentis, eles definitivamente devem ser usados. Seu significado é mais fácil de entender do que a média combinada com o desvio padrão, resultando em uma análise mais rápida e melhor

## Collecting Performance Data

A primeira etapa no gerenciamento de desempenho é coletar medições adequadas. Como há uma variedade de abordagens de medição diferentes, examinaremos as mais amplamente utilizadas para entender quando usá-las e como são interpretadas.

### JMX

Java Management Extension (JMX) é uma tecnologia padrão que faz parte da JVM desde Java 5. Ela torna possível gerenciar e monitorar aplicativos Java e recursos do sistema. Os recursos são representados como os chamados MBeans (Managed Beans). A arquitetura JMX consiste em três camadas.

- O nível de instrumentação consiste nos MBeans reais. Esta é a única camada com a qual você normalmente entra em contato.

- O nível do agente ou servidor MBean, que representa a interação entre os MBeans e o aplicativo.
- O nível de serviço distribuído, que permite acesso aos MBeans por meio de conectores e adaptadores.

![Overview of JMX: Illustration of the JMX Management Specification, v1.0](https://dt-cdn.net/images/jmxarchitecture-510-f508eca56d.png)

Figura 1.5: Visão geral do JMX: Ilustração da especificação de gerenciamento JMX, v1.0

As ferramentas de gerenciamento JMX fornecem acesso aos MBeans - ou seja, os dados de monitoramento reais - de um JVM local ou remoto. JConsole e VisualVM, partes do JDK, fornecem uma maneira simples de consultar os valores de desempenho de um processo, que podem ser acessados por meio dos MBeans.

![JConsole uses JMX to access the MBeans of a Java application.](https://dt-cdn.net/images/jconsole-510-2bdedea39e.png)

### Using JVMPI/JVMTI to Collect Performance Data

O Java Virtual Machine Tooling Interface (JVMTI) foi introduzido com o Java 5 e substitui o Java Virtual Machine Profiler Interface (JVMPI), que estava disponível nas versões anteriores do JVM. . JVMTI é uma interface nativa e permite que os agentes C/C++ acessem o estado interno da JVM, registrem-se para eventos e modifiquem o bytecode. Os provedores de ferramentas usam essa interface para depuração, criação de perfil e monitoramento, pois fornece acesso universal aos componentes internos da JVM. À medida que a interface executa o código nativo, a sobrecarga para recuperar dados de desempenho pode ser mantida muito baixa, dependendo de como os dados são recuperados.

Quando a JVM é iniciada, o agente JVMTI nativo, que é especificado por meio de uma opção de linha de comando JVM, é carregado no processo Java. O agente então se registra para todos os eventos de seu interesse e recebe retornos de chamada enquanto o aplicativo está em execução.

Também existe a opção de implementar agentes em Java. Eles podem ser usados facilmente em várias plataformas. Os agentes Java, entretanto, não possuem alguns dos recursos disponíveis apenas para a interface nativa. A função mais importante usada por ferramentas de desempenho - instrumentação de bytecode - é possível usando agentes nativos e Java.

### Bytecode Instrumentation

A instrumentação de bytecode refere-se à alteração do bytecode Java de uma classe Java antes ou enquanto ela é executada pelo Java Runtime. Essa abordagem é amplamente usada por ferramentas de profiling, diagnóstico e monitoramento. Para medir o tempo de execução de um método específico, o bytecode do método é alterado adicionando o código Java no início e no final do método. O código instrumentado é executado junto com o método e o tempo de execução registrado.

![Bytecode instrumentation makes it possible to insert special monitoring code.](https://dt-cdn.net/images/bytecodeinstrumentation-556-54129dab05.png)

Figura 1.7: A instrumentação Bytecode torna possível inserir um código de monitoramento especial O bytecode de um aplicativo pode ser alterado em diferentes estágios do ciclo de vida do aplicativo. Vejamos três abordagens:

- Podemos alterar estaticamente o bytecode como parte do processo de construção. Esta é a abordagem menos flexível. Também requer acesso às fontes que devem ser reconstruídas. Cada alteração da instrumentação também requer uma reimplantação do aplicativo, o que geralmente não é fácil de fazer e pode ser demorado.
- A segunda abordagem é modificar o bytecode quando a classe é carregada pela JVM: JVMTI torna possível modificar o bytecode antes que a classe seja realmente usada sem ter que alterar o arquivo de classe real. Essa abordagem é mais conveniente, pois não há mudanças necessárias no processo de construção e nos artefatos de implementação necessários, como arquivos jar. Quase todas as ferramentas modernas seguem essa abordagem.
- A terceira abordagem é instrumentar dinamicamente as classes que já foram carregadas e estão atualmente em uso pela JVM. Isso nos permite alterar o bytecode em tempo de execução sem precisar reiniciar o aplicativo, o que é especialmente útil quando um aplicativo deve ser depurado na produção. E como as informações de diagnóstico de nível de depuração podem ser ativadas e desativadas no tempo de execução, as reinicializações do aplicativo tornam-se desnecessárias.

### Measurement from the End-User Perspective

O monitoramento de desempenho de aplicativos modernos vai além de medir o desempenho apenas dentro do aplicativo, incluindo medições da perspectiva do usuário. Se considerarmos o tempo de resposta, a métrica mais importante para o desempenho da perspectiva do usuário, veremos apenas uma fração da resposta geral se medirmos apenas no lado do servidor - normalmente algo entre 20 e 50 por cento. Por exemplo, perdemos o tempo de transmissão da rede e os tempos de carregamento de recursos.

![Response time is the time elapsed from sending the request to the complete receipt of the response.](https://dt-cdn.net/images/end-to-end-responsetime-373-2f3b1eaf67.png)

Figura 1.8: O tempo de resposta é o tempo decorrido desde o envio da solicitação até o recebimento completo da resposta.

Os detalhes de tempo, como quanto tempo leva para o servidor retornar um documento HTML, não são de interesse para os usuários finais. Em vez disso, eles estão muito mais cientes do tempo total que leva para carregar uma página, todo o conteúdo, incluindo todas as imagens e outros conteúdos dinâmicos.

Na figura a seguir, vemos todas as atividades associadas ao carregamento de uma página. Isso inclui carregar o documento, imagens, bem como executar JavaScript e renderizar o conteúdo. Neste exemplo, a otimização do servidor não impedirá que os usuários finais esperem muito pela execução do JavaScript antes que a página seja completamente carregada para eles.

![Both browser and server activities impact response time of a page.](https://dt-cdn.net/images/contentbreakdown-434-a219308de1.png)

Figura 1.9: As atividades do navegador e do servidor afetam o tempo de resposta de uma página.

As tecnologias variam muito, mas estão todas agrupadas nos termos monitoramento do usuário final, "monitoramento do usuário real https://www.dynatrace.com/platform/real-user-monitoring/" ou "monitoramento da experiência do usuário". Resumindo, todos eles são usados para medir o desempenho mais próximo do usuário final. Isso pode ser feito monitorando o tráfego da rede https://www.dynatrace.com/platform/network-monitoring/, capturando dados de desempenho nos navegadores dos usuários ou amostrando dados de vários locais remotos.

Antes de examinarmos os diferentes métodos em detalhes, temos que definir quais perguntas essas ferramentas devem ser capazes de responder:

- Quanto tempo leva para carregar uma página?
- Houve algum erro ou problema no carregamento da página?
- Quanto tempo levou para concluir certas interações do usuário na página?

Esses dados podem ser coletados de várias maneiras diferentes, e precisamos olhar para as vantagens e desvantagens de cada uma, especialmente porque são em grande parte complementares.

### Synthetic Monitoring

O monitoramento sintético envolve a execução de scripts que imitam transações típicas do usuário em locais ao redor do globo. O desempenho dessas transações é medido e usado para tirar conclusões sobre como os sites se comportam para usuários reais. Podemos derivar tendências e fazer declarações sobre o desempenho geral e a disponibilidade do aplicativo.

A distribuição global dos pontos medidos também permite identificar problemas regionais. Portanto, a qualidade da medição depende fortemente do número de locais. Quanto mais e mais perto do usuário final, melhor.

A maior deficiência do monitoramento sintético é que ele só pode ser usado para um subconjunto de transações do usuário. As transações que têm efeitos colaterais, como o acionamento de um pedido, não podem ser monitoradas. É óbvio que você não deseja que seu monitoramento acione remessas reais em um aplicativo de comércio eletrônico.

A outra desvantagem dessa abordagem é o esforço necessário para manter os scripts. Sempre que a lógica do aplicativo é alterada, os scripts correspondentes também devem ser modificados. Embora uma abordagem de definição de script modular torne essa tarefa mais fácil, o monitoramento sintético ainda requer manutenção constante.

![Synthetic monitoring with simulated users](https://dt-cdn.net/images/syntheticmonitoring-350-59d356ca75.png)

Figura 1.10: Monitoramento sintético com usuários simulados

### Network-Centric Monitoring

Em contraste com o monitoramento sintético, o monitoramento centrado na rede (ilustrado na figura a seguir) mede os tempos de resposta reais para usuários reais, analisando o tráfego de rede entre navegadores e servidores de back-end. O monitoramento dos tempos de resposta e erros relacionados à rede para cada solicitação individual nos permite medir os tempos de transmissão da rede e detectar erros de aplicativo enviados de volta como respostas às solicitações do usuário.

Imagine que um cliente receba uma mensagem de erro ao comprar um item em um site. Se o usuário entrar em contato com o suporte ao cliente do site, será difícil determinar o que aconteceu com esse usuário específico. Com o monitoramento passivo, o caso ideal, o problema pode ser encontrado automaticamente observando o tráfego real da rede.

Dispositivos especiais, hardware e virtuais, são usados para coletar dados de desempenho no nível da rede. Esses "detectores de rede" registram a comunicação da rede e, assim, reconstroem o caminho do clique para um usuário. Algumas soluções permitem combinar esses dados com a instrumentação do navegador, o que é especialmente útil para aplicativos AJAX de página única, onde o comportamento do usuário não pode ser analisado com base na comunicação de rede ou apenas com dificuldade.

![Network-centric monitoring using a network sniffer](https://dt-cdn.net/images/network-centric-monitoring-306-03b26e98b9.png)

Monitoramento centrado na rede usando um sniffer de rede

A maior deficiência do monitoramento centrado na rede é que os tempos de resposta ainda são medidos no datacenter. Os problemas de desempenho decorrentes de problemas de conectividade nas redes do usuário são difíceis de analisar. Outro problema com essa abordagem é a falta de percepção do impacto do conteúdo de terceiros - como anúncios ou widgets sociais - no desempenho do aplicativo https://www.dynatrace.com/platform/application-performance-monitoring/.

### Browser Instrumentation

Essa abordagem está se tornando mais popular, pois pode coletar dados de desempenho diretamente do navegador por meio de instrumentação HTML e JavaScript. As páginas solicitadas já contêm código de monitoramento ou ele é inserido no código HTML durante o tempo de execução. A vantagem dessa abordagem é que os tempos de resposta exatos podem ser coletados para cada usuário do aplicativo. A especificação W3C Navigation Timing https://confluence.dynatrace.com/community/display/DOCDT61/W3C+Navigation+Timing+Metrics?_ga=2.216006421.347833606.1630261483-1435161920.1630156647 define uma abordagem padronizada para coletar esses dados. Atualmente, a especificação Web Timing já está implementada na maioria dos navegadores modernos, como Chrome, Internet Explorer e Firefox.

Existe até uma implementação móvel para o IE no Windows Mango, e a implementação do Android está em seus primeiros passos. Mesmo que um navegador não tenha implementado o padrão, estruturas como o Boomerang http://hacks.bluesmoon.info/boomerang/doc/ ou as informações expostas pela Barra de Ferramentas Google podem ser usadas para coletar dados semelhantes.

Os dados são enviados de volta ao servidor por meio de XHR ou por meio de um sinalizador denominado. Um beacon representa uma URL simples que é solicitada de um servidor. Os dados de medição são empacotados como parâmetros de URL. Um exemplo de tal beacon é http://www.myserver.com/beacon?loadTime=2000. Neste caso, o tempo de carregamento é enviado ao servidor como parâmetro. A Instrumentação do navegador é a única abordagem de monitoramento que coleta dados reais de desempenho do usuário. Portanto, ele fornece a medição mais precisa. No entanto, não pode ser usado para monitorar a disponibilidade, pois as medições são coletadas apenas quando as páginas são entregues aos usuários. Quando o servidor, entretanto, não estiver acessível, nenhuma página será solicitada.

## Collecting and Analyzing Execution-Time Data

Existem duas técnicas amplamente utilizadas para coletar dados de tempo de execução: Medição Baseada no Tempo e Medição Baseada em Eventos. Ambos se tornaram partes padrão dos produtos de gerenciamento de desempenho e normalmente representam os dados como pilhas, de modo que parecem superficialmente semelhantes. Examinaremos cada um em detalhes, apontando as diferenças fundamentais em suas abordagens de medição de dados e o que isso significa em termos de interpretação de dados.

### Time-Based Measurement

Na medição baseada em tempo, as pilhas de todos os threads em execução de um aplicativo são capturadas em um intervalo constante (taxa de amostragem). Em seguida, a pilha de chamadas é examinada e o método superior é definido para o estado da thread - em execução ou em espera. Quanto mais frequentemente um método aparecer em uma amostra, maior será o tempo de execução ou espera.

Cada amostra dá uma indicação de qual método estava executando ativamente, mas não há medidas de duração da execução ou de quando um método conclui a execução. Isso tende a perder chamadas de método de execução mais curta, como se eles não estivessem executando ativamente. Ao mesmo tempo, outros métodos podem ser sobrecarregados porque acontecem de estar ativos durante, mas não entre os períodos de amostragem. Por ambos os motivos, isso torna difícil coletar dados de tempo de execução exato para um método individual.

O intervalo de tempo selecionado determina a granularidade e a qualidade da medição. Um tempo de medição mais longo também aumenta a probabilidade de que os dados sejam mais representativos e de que os erros mencionados tenham menos efeito. Esta é uma estatística simples. Quanto maior o nosso número de amostras, mais provável é que os métodos executados com mais frequência apareçam como métodos de execução na pilha de chamadas.

![The stacks of each thread are analyzed every 20 ms](https://dt-cdn.net/images/sampling-510-713a324b73.png)

Figura 1.12: As pilhas de cada thread são analisadas a cada 20 ms.

Essa abordagem cria seus próprios erros de medição, mas fornece uma maneira direta de rastrear problemas de desempenho. Talvez a vantagem mais importante seja que a sobrecarga de medição pode ser controlada pelo intervalo de amostragem; quanto maior o intervalo, menor será a sobrecarga. Essa baixa sobrecarga é especialmente importante em ambientes de produção sob carga total. Aqui está o porquê:

Vemos que uma determinada solicitação do usuário é lenta, mas não podemos ver os dados de entrada em uso. Ao mesmo tempo, não temos conhecimento das contagens de execução reais para métodos específicos. Medidas baseadas no tempo não podem fornecer essas informações. Os dados coletados não nos fornecem nenhuma maneira de determinar quando a execução de um único método é lenta ou quando há execução de método excessivamente frequente. Uma análise de problema desses tipos de detalhes requer o uso de medição baseada em eventos.

### Event-Based Measurement

Em vez de amostras de rosca, a medição baseada em eventos analisa as execuções de métodos individuais. Registramos um timestamp no início e no final de cada chamada de método, o que torna possível contar o número de vezes que métodos individuais são chamados e determinar o tempo exato de execução de cada chamada. É uma questão simples de calcular as diferenças entre os carimbos de data/hora coletados.

![For event-based measurement, the start and end time for each method call is logged. ](https://dt-cdn.net/images/event-based-measurement-510-ce50b36d85.png)

Figura 1.13: Para medição baseada em eventos, a hora de início e término para cada chamada de método é registrada.

O Java Runtime oferece callbacks especiais via JVMPI/JVMTI, que são chamados no início e no final das execuções do método. No entanto, essa abordagem carrega uma alta sobrecarga que pode ter um grande impacto no comportamento do tempo de execução do aplicativo. Os métodos modernos de medição de desempenho usam instrumentação de bytecode, exigindo muito pouca sobrecarga. Há também a funcionalidade adicional de instrumentar os métodos mais relevantes, apenas, e a capacidade de capturar informações de contexto, como parâmetros de método.

### Transactional Measurement

Para aplicações complexas, usamos formas mais sofisticadas de medição baseada em eventos. Portanto, além de armazenar as métricas de execução do método, a medição transacional armazena o contexto, o que torna possível examinar os métodos de diferentes transações individualmente. Dessa forma, podemos descobrir que uma instrução SQL lenta específica é executada apenas para uma solicitação da Web específica.

As execuções de métodos também são armazenadas em sua ordem cronológica individual em vez de serem agregadas com base na pilha de chamadas, o que pode revelar o comportamento dinâmico da lógica de processamento de uma transação - por exemplo, uma solicitação de servlet. O exame isolado de transações individuais, portanto, torna possível analisar problemas dependentes de dados e outliers.

Os diagnósticos detalhados fornecidos pela medição transacional são particularmente adequados para trabalhar com aplicações distribuídas. Existem várias maneiras pelas quais as transações entre camadas são correlacionadas:

As abordagens baseadas no tipo de transação passam um ID do tipo de transação para a próxima camada e, adicionalmente, usam a correlação baseada no tempo. Isso fornece mais detalhes, não apenas o tempo, mas também o tipo de transação (por exemplo, URL) é levado em consideração. A correlação baseada em ID de transação passou IDs de transação individuais para a próxima camada. Isso permite seguir transações individuais entre camadas e é a abordagem mais precisa para a correlação entre camadas.

![Each method is logged using start and end time to calculate total execution time](https://dt-cdn.net/images/transactional-measurement-510-653655ddfa.png)

Figura 1.14: Cada método é registrado usando o horário de início e término para calcular o tempo total de execução.

### Hybrid Approaches (Abordagens Híbridas)

Os métodos híbridos combinam medição de desempenho baseada em tempo e em eventos para explorar as vantagens de ambos e evitar as desvantagens individuais. A principal vantagem da coleta de dados baseada em eventos reside na captura de cada chamada e seu contexto - como parâmetros de método. A principal vantagem da abordagem baseada no tempo reside na sobrecarga constante e independente da carga.

A medição híbrida combina dados baseados em eventos transacionais com instantâneos de thread que podem ser atribuídos a uma transação específica. A instrumentação garante que possamos capturar o contexto transacional, bem como os parâmetros do método e outros detalhes, enquanto os dados baseados no tempo fornecem uma abordagem de baixo overhead para capturar as informações do tempo de execução.

Essa abordagem oferece o insight mais preciso sobre o comportamento do tempo de execução de um aplicativo, e a sobrecarga quase constante é adequada para monitoramento de produção. A figura mostra um rastreamento transacional que combina as duas fontes de dados uma com a outra. Os dados baseados em eventos são mostrados em azul e os dados baseados em tempo em verde.

![Hybrid transactional trace (green portions were added via thread snapshots)](https://dt-cdn.net/images/hybrid-data-collection-thumb-400-cdfeabf909.png)

Figura 1.15: Traço transacional híbrido (as partes verdes foram adicionadas por meio de instantâneos de encadeamento)

## Visualizing Performance Data

Além de medir os dados de desempenho, também temos que visualizá-los para análise. Dependendo da estrutura de dados subjacente, diferenciamos dois tipos de dados para visualização:

- Dados baseados em tempo, como valores JMX, são representados como relações, ou tuplas, em que o valor dos dados é pareado com um carimbo de data/hora (timestamp). Portanto, para cada medição de, por exemplo, carga da CPU, uso da rede ou contagem de transações (transaction count), pode haver apenas um único valor ou ponto de dados. Dependendo do caso de uso, podemos visualizar esses dados usando vários tipos de gráfico: linha, medidor (meter), semáforo (traffic lights), o que for necessário para plotar essas informações para um determinado caso de uso.

- Os dados de tempo de execução são representados hierarquicamente como uma ordenação de métodos e tempos de execução associados - uma árvore de chamada. Na verdade, todas as medições de tempo de execução que mencionamos podem ser visualizadas como árvores. Observe, entretanto, que a semântica para ler essas árvores diferentes nem sempre é a mesma.

Não cobriremos a representação dos dados de monitoramento baseados no tempo em mais detalhes, pois é muito fácil de entender. No entanto, as diferentes representações para dados de tempo de execução requerem um pouco mais de explicação.

### Aggregating Execution Data in Call Trees

Os dados de execução baseados em tempo e eventos podem ser representados como árvores de chamada (call trees). Isso ilustra bem a hierarquia de chamadas, mas ao custo de obscurecer a sequência real de chamadas individuais.

As árvores de chamada são usadas principalmente para ajudar a identificar pontos de acesso de execução - aquelas áreas onde os métodos são chamados com mais frequência e têm os maiores tempos de execução.

Em termos práticos, problemas devido à alta utilização da CPU ou longos tempos de espera causados por sincronização demorada são facilmente diagnosticados com árvores de chamada. No entanto, a perda de informações da sequência de chamadas torna impossível usar árvores de chamadas para encontrar problemas de fluxo de controle lógico.

Por um lado, usando árvores de chamadas, devemos ser capazes de identificar um ponto de acesso do aplicativo causado por uma chamada de banco de dados complexa e de longa duração. Por outro lado, as árvores de chamada provavelmente não iluminarão um problema decorrente de um alto número de perdas de cache que exigem consultas repetidas do banco de dados. O método de perda de cache provavelmente é perdido nos pontos de acesso, uma vez que não consome muito tempo de execução.

Dependendo da ferramenta de desempenho usada, você também pode ter informações adicionais disponíveis, como instruções de banco de dados chamadas, URLs específicos ou outros dados importantes de diagnóstico. Há também uma certa quantidade de dados e personalização de relatórios disponíveis. O uso desses dados pode ajudar a distinguir os dados das chamadas e permitir uma análise melhor e mais simples.

![The call to HashMap.get requires the most CPU time; the return call tree shows what the calls were.](https://dt-cdn.net/images/statistical-call-tree-510-0157661de0.png)



Figura 1.16: HashMap.get requer mais tempo de CPU; a árvore de chamada de retorno mostra quais foram as chamadas.

Com relação a problemas de desempenho complexos, entretanto, rapidamente se atinge os limites dessa abordagem.

### Visualizing Individual Call Traces

O uso de métodos de medição transacional permite mostrar a seqüência de chamadas de cada transação ou solicitação individualmente. A representação visual será muito semelhante a uma árvore de chamada, mas os dados são muito mais precisos e, portanto, a visualização será mais precisa.

Os rastreamentos de chamadas mostram a sequência oportuna de execução do método, que fornece uma representação visual do fluxo lógico baseado em transações. Com esta técnica de visualização, problemas complexos, como o descrito acima, não são mais impossíveis de diagnosticar! Além disso, como podemos ver no exemplo abaixo, essas informações também podem ser usadas para diagnosticar problemas funcionais, como uma exceção lançada no contexto de uma chamada de serviço remoto.

![A transactional trace visualizes the execution of a request, including context information.](https://dt-cdn.net/images/transactional-trace-510-301228a882.png)



Figura 1.17: Um rastreamento transacional visualiza a execução de uma solicitação, incluindo informações de contexto.



### Showing Complex Transactions as Transaction Flows

Particularmente em ambientes distribuídos, as visualizações de rastreamento de chamadas fornecem uma visão inestimável das características dinâmicas do desempenho do aplicativo https://www.dynatrace.com/platform/application-performance-monitoring/. No entanto, quando usado em um ambiente de produção onde há uma carga constante de milhares de transações por segundo, não é mais viável examinar transações individuais.

Isso nos leva ao reino das visualizações do fluxo de transações, que, como o nome indica, são capazes de representar o fluxo de transações individuais ou agregadas por meio de um aplicativo. Conforme mostrado na figura abaixo, esta visualização é usada para analisar problemas de desempenho em ambientes complexos, de alto volume e distribuídos.

![Transaction Flow of a Transaction through an Application Landscape](https://dt-cdn.net/images/transaction-flow-thumb-320-c413f6c5e7.png)



Figura 1.18: Fluxo de transação de uma transação por meio de um cenário de aplicativos

## Controlling Measurement Overhead

A medição de desempenho tem um custo, que pode variar dependendo da abordagem empregada. Por exemplo, é impossível medir o tempo de execução de um método e, em seguida, processar e armazenar os dados sem criar sobrecarga. Podemos perceber essa carga extra na forma de tempos de resposta mais longos, maior utilização da CPU, maior uso da memória ou como carga adicional de rede.

Além do impacto no desempenho, a sobrecarga pode resultar nos chamados Heisenbugs, batizados com o nome do princípio da incerteza de Heisenberg, em que os problemas aparecem ou desaparecem como resultado da medição. Há um risco inevitável, embora pequeno, de que a sobrecarga de medir o desempenho o prejudique. Vamos examinar os diferentes tipos de sobrecarga que podem ocorrer e ver como as medidas de desempenho podem afetar o comportamento do tempo de execução de um aplicativo.

### Controlling Response-Time Overhead

O aumento dos tempos de resposta tem o efeito mais direto sobre os usuários, portanto, a sobrecarga do tempo de resposta é a mais crítica. Geralmente, quanto mais medimos, maior é a sobrecarga. Isso é especificamente verdadeiro para todas as medidas baseadas em eventos tomadas durante a execução do código do aplicativo. Quando usamos uma abordagem de medição baseada em instrumentação, não devemos instrumentar métodos com uma frequência de chamada muito alta e um tempo de execução individualmente curto. A sobrecarga será maior neste caso.

![The shorter the method, the greater its percentage share of the instrumented code.](https://dt-cdn.net/images/instrumentation-overhead-568-e723529c26.png)

Figura 1.19: Quanto mais curto for o método, maior será a participação percentual do código instrumentado

Por exemplo, se o código de diagnóstico instrumentado requer um milissegundo, a sobrecarga para um tempo de execução de método de 100 ms é de um por cento. Se o método levar apenas 10 ms, já estamos com uma sobrecarga de 10 por cento. A sobrecarga do tempo de resposta geralmente é descrita em porcentagens, por exemplo, 3% - 5% para um sistema de produção e um pouco mais para um ambiente de teste. Você pode descrever a sobrecarga como uma porcentagem da resposta real, mas esse valor pode ser difícil de contextualizar. Ainda assim, em muitos casos, referir-se a números reais é mais fácil. Por exemplo, é mais descritivo dizer que o monitoramento torna o aplicativo 20 ms mais lento do que dizer que um aplicativo tem uma sobrecarga de 2%.

### Controlling CPU and Memory Overhead

A medição de desempenho não afeta apenas os tempos de resposta, mas também afeta o uso da CPU e da memória. Quanto mais lógica de medição é executada, mais CPU e memória são retirados do aplicativo.

As ferramentas de monitoramento usam duas estratégias básicas para reduzir a sobrecarga. Uma é simplesmente amostrar uma porcentagem das transações. Isso tem a desvantagem óbvia de perdermos a visibilidade total do aplicativo. A abordagem mais sofisticada é descarregar o processamento com uso intensivo de CPU (offload CPU) e memória para servidores dedicados, deixando apenas a captura do carimbo de data/hora (timestamp) no aplicativo.

### Controlling Network Overhead

A maioria das ferramentas de desempenho envia os resultados do monitoramento pela rede para um servidor centralizado. Quanto maior a quantidade de dados medidos, maior o volume de dados transferidos pela rede. O intervalo de medição e o número de medições também afetam a quantidade de dados transferidos.

Vejamos os efeitos do envio de medições baseadas em eventos de chamadas de método pela rede. Temos 1.000 usuários trabalhando com o aplicativo, resultando em 100.000 execuções de métodos medidas por segundo. Se, em uma abordagem ingênua, o nome do método (média de 15 caracteres) e a duração da execução são enviados em um tipo de dados de 8 bytes para cada método, isso resulta em 100.000 * (15 + 8) = 2,3 MB/s . Esse alto uso da rede pode, e geralmente terá, efeitos colaterais sérios no comportamento do aplicativo.

Isso pode não ser um problema em uma carga pequena ou mesmo em um ambiente de área de trabalho local. No entanto, ao monitorar grandes ambientes de produção, precisamos de métodos altamente otimizados nos quais apenas alguns bytes são transmitidos.

Para reduzir o tráfego da rede, alguns métodos de medição agregam dados dentro do aplicativo antes de enviá-los a um servidor centralizado. Infelizmente, essa abordagem tem a desvantagem de consumir recursos adicionais que podem ser necessários para o aplicativo.

### Measuring Overhead

A maneira mais direta de medir a sobrecarga é executar o mesmo teste de carga duas vezes; uma vez contra os aplicativos não instrumentados e uma vez contra os aplicativos instrumentados. Durante a execução do teste, meça os tempos de resposta, CPU, memória e utilização da rede. Comparar as medições desses dois testes fornece uma medida de sobrecarga (overhead).

## The Theory Behind Performance

Finalmente, devemos mergulhar nos fundamentos teóricos da gestão de desempenho https://www.dynatrace.com/platform/application-performance-monitoring/. É essencial para nosso trabalho diário compreender algumas das leis imutáveis de desempenho de aplicativos https://www.dynatrace.com/platform/application-performance-monitoring/. Prometemos limitar esta discussão às considerações mais importantes e práticas.

### Performance Modeling Using Queuing Theory

Modelos de enfileiramento são freqüentemente usados para modelar as características de um sistema de software. Embora apresentem uma simplificação, são úteis para entender as características dos sistemas de software.

A idéia básica é simples. Um aplicativo requer recursos para atender a uma solicitação e esses recursos estão disponíveis em quantidade limitada. Se um recurso não estiver disponível, uma solicitação deve aguardar por ele. A figura abaixo mostra um modelo de enfileiramento simples para um aplicativo de camada única. Os recursos incluem um pool com threads de servlet para o banco de dados, memória e CPU. Eles são interdependentes, portanto, se o pool de threads for muito pequeno, não ajudaria se mais CPU estivesse disponível.

![In an application, various queues require waiting.](https://dt-cdn.net/images/queueing-model-263-6e60b088c0.png)

Figura 1.20: Em um aplicativo, existem diferentes filas que exigem espera.

O que podemos concluir disso? O desempenho e a escalabilidade de nosso aplicativo dependem dos recursos disponíveis. Quando há menos recursos disponíveis, mais intensamente o que está disponível será usado. Isso pode levar rapidamente a um dimensionamento insatisfatório do aplicativo e a tempos de execução cada vez maiores.

Enquanto um recurso está em uso, não está disponível para outras solicitações. Esses recursos limitados têm o potencial de se tornarem gargalos de desempenho e é por isso que se tornam um foco importante para monitoramento e dimensionamento de aplicativos. Quando a resposta do aplicativo fica mais lenta, quase sempre é devido à falta de recursos.

É por isso que é tão importante ficar de olho na utilização de recursos individuais e monitorar as transações que os utilizam. Depois de construir esse modelo, podemos detectar e eliminar quase todos os problemas de desempenho e escalabilidade. Modelos de enfileiramento também são a base para dois limites importantes de desempenho e escalabilidade.

### Little's Law

Little's Law afirma que, dado um sistema de fila em um estado estável, o número médio de clientes, ou solicitações, é igual ao produto de sua taxa média de chegada (average arrival rate) e o tempo médio (average time) gasto no sistema. Simplificando, um sistema é estável quando o número de novas solicitações não é maior do que a carga máxima que pode ser processada.

Por exemplo, quando leva meio segundo para processar uma solicitação, no máximo duas solicitações podem ser processadas por segundo. Se, por um longo período de tempo, mais de duas solicitações por segundo forem recebidas, o sistema ficará instável.

Isso pode parecer trivial e lógico, mas como isso nos ajuda em nosso trabalho diário? Primeiro, podemos usá-lo para estimar a escalabilidade. Sabemos que temos dois núcleos disponíveis para um aplicativo, o que significa 2.000 ms de tempo de CPU por segundo. Se uma solicitação requer 200 ms, nossa carga estável máxima é de dez solicitações. Portanto, podemos determinar muito facilmente o ponto em que nossos recursos estão saturados. Esta já é uma forma básica de planejamento de capacidade. Embora seja muito simplificado, ajuda como um primeiro palpite.

Outro caso de uso interessante é para verificação de uma configuração de teste. Podemos usar a Lei de Little para determinar se os resultados do teste são válidos e não afetados por um driver de teste sobrecarregado. Se um teste de carga mostrar que tivemos no máximo cinco solicitações por segundo, cada uma levando 200 ms em um sistema com dez threads, saberemos que algo está errado. De acordo com a Lei de Little, deveríamos ter dez vezes a taxa de transferência, pois tínhamos 10.000ms de tempo disponível nos dez threads. Neste caso, o gerador de carga não conseguiu colocar nosso sistema sob uma carga apropriada e o teste não representa o limite de nossa aplicação.

## Amdahl's Law

A Lei de Amdahl define o efeito da paralelização no tempo de resposta de um sistema. Vamos ver um exemplo. Se pudermos paralelizar 200 ms de uma solicitação que leva 500 milissegundos e dividir a execução em 4 processos, alcançamos um tempo de resposta de 350 (300 + 200/4) milissegundos.

Este conceito é explorado principalmente em sistemas de grade e grandes lotes, nos quais um alto grau de paralelização pode ser alcançado. Quanto mais altas as proporções de paralelização, maiores são os efeitos.

A Lei de Amdahl não pode ser usada 1: 1 para a quantidade de tempo de execução ganho. No entanto, considerações análogas nos ajudam a entender quais efeitos a paralelização tem em um aplicativo. Também pode ser usado para uma estimativa da eficácia. Em um ponto específico, a paralelização não faz mais sentido porque o ganho no desempenho geral é muito baixo.

## How Humans Perceive Performance

Falamos muito sobre os aspectos técnicos da medição de desempenho. Embora isso seja importante, é importante lembrar que os usuários são os juízes finais do desempenho. Como os usuários não são relógios, sua percepção de tempo pode ser diferente do que medimos empiricamente. Se você estiver interessado em uma discussão detalhada deste tópico, recomendamos o livro Designing and Engineering Time: The Psychology of Time Perception in Software, de Steven C. Seow.

Ao falar sobre a percepção do tempo humano, devemos primeiro estar cientes de que nós (humanos) sempre comparamos o tempo percebido com a nossa expectativa de quão rápido as coisas deveriam ser. Portanto, o tempo para nós é sempre relativo. Julgamos a capacidade de resposta do sistema comparando as expectativas com nossas percepções de tempo de resposta.

Ao julgar a capacidade de resposta do sistema, devemos primeiro saber quão rápido os usuários esperam que um sistema seja. A pesquisa mostra que existem quatro categorias principais de tempos de resposta:

- Esperamos uma resposta instantânea, 0,1 a 0,2 milissegundos, para qualquer ação semelhante a uma interação física. Pressionando um botão, por exemplo. Esperamos que este botão indique que foi pressionado neste período.
- Esperamos uma resposta imediata, dentro de meio a um segundo, indicando que nossas informações foram recebidas. Mesmo no caso de uma interação simples, esperamos uma resposta real ao nosso pedido. Isso é especialmente verdadeiro para informações que presumimos já estar disponíveis, como ao paginar ou rolar o conteúdo.
- Esperamos uma resposta dentro de 2 a 5 segundos para qualquer outra solicitação interativa. Se a resposta demorar mais, começamos a sentir que a interação é complicada. Quanto mais complexa percebermos que uma tarefa é, mais estaremos dispostos a esperar pela resposta.

Quando se trata de desempenho do sistema, os usuários têm expectativas muito precisas e essas métricas são úteis para entender nossas percepções de desempenho e para uso nas definições de SLA. Pode ser difícil encontrar SLAs ou limites de tempo de resposta adequados, mas ter essas categorias precisas nos dá uma abordagem direta para definir os limites de desempenho.

### What It Means to Be Faster

Tal como acontece com o desempenho, a percepção humana para mais rápido ou mais lento não é tão precisa quanto as medições técnicas. Isso é muito importante ao julgar se uma otimização de desempenho vale o esforço.

Em essência, nossa percepção limitada da capacidade de resposta do aplicativo não pode detectar alterações de desempenho de muito menos do que 20%. Podemos usar isso como uma regra ao decidir quais melhorias afetarão os usuários de forma mais perceptível. Se otimizarmos 300 milissegundos para uma solicitação que levou 4 segundos, podemos supor que muitos usuários não perceberão nenhuma diferença no tempo de resposta. Ainda pode fazer sentido otimizar o aplicativo, mas se nosso objetivo é tornar as coisas mais rápidas para os usuários, temos que torná-lo mais rápido em pelo menos 0,8 segundos.

# Chapter: Memory Management

## How Java Garbage Collection Works

O Java Memory Management, com sua coleta de lixo integrada, é uma das melhores realizações da linguagem. Ele permite que os desenvolvedores criem novos objetos sem se preocupar explicitamente com a alocação e desalocação de memória, porque o coletor de lixo automaticamente recupera a memória para reutilização. Isso permite um desenvolvimento mais rápido com menos código clichê, ao mesmo tempo que elimina vazamentos de memória e outros problemas relacionados à memória. Pelo menos em teoria.

Ironicamente, a coleta de lixo Java parece funcionar muito bem, criando e removendo muitos objetos. A maioria dos problemas de gerenciamento de memória são resolvidos, mas geralmente ao custo de criar sérios problemas de desempenho. Tornar a coleta de lixo adaptável a todos os tipos de situações levou a um sistema complexo e difícil de otimizar. Para entender a coleta de lixo, você precisa primeiro entender como funciona o gerenciamento de memória em uma Java Virtual Machine (JVM).

### How Java Garbage Collection Really Works

Muitas pessoas pensam que a coleta de lixo coleta e descarta objetos mortos. Na realidade, a coleta de lixo Java está fazendo o oposto! Objetos vivos são rastreados e tudo o mais é designado como lixo. Como você verá, esse mal-entendido fundamental pode levar a muitos problemas de desempenho.



*https://www.dynatrace.com/resources/ebooks/javabook/how-garbage-collection-works/



























