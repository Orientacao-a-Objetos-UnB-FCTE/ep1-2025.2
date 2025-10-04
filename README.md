# 🏥 Trabalho Prático – Sistema de Gerenciamento Hospitalar  

### 🎯 Objetivo  
Implementar um *Sistema de Gerenciamento Hospitalar* em *Java, aplicando conceitos avançados de **Programação Orientada a Objetos (POO), com foco em **herança, polimorfismo, encapsulamento, persistência de dados* e *regras de negócio mais complexas*.  

---
## Descrição do Projeto

Desenvolvimento de um sistema de gerenciamento hospitalar utilizando os conceitos de orientação a objetos (herança, polimorfismo e encapsulamento) e persistência de dados em arquivos.

## Dados do Aluno

- **Nome completo:** Luiz Gustavo da Conceição Souza
- **Matrícula:** 242015915
- **Curso:** Engenharias
- **Turma:** 02 24M5 24T1

---

## Instruções para Compilação e Execução

1. **Compilação:**  
`javac hospital.terminal.Main.java`

2. **Execução:**  
`java hospital.terminal.Main`

3. **Estrutura de Pastas:**  
 - Pasta hopsital: endidade(Consulta, Exame, Internacao, Medicos, Pacientes)
                   interfac(Medicosinterface, PacienteInterface)                   
                   servico(Agendamento, AgendamentoExame, GerenciarInternacao)
                   terminal(Main)  
                   util(Excecao)  
 - Pasta out: (consultas.csv) (exames.csv) (internacoes.csv) (medicos.csv) (pacientes.csv)   

3. **Versão do JAVA utilizada:**  
   - Oracle JDK 24.0.2 (openjdk-24)

---

## Vídeo de Demonstração

https://youtu.be/NmCfBb60ygg

---

## Prints da Execução

1. Menu Principal:  
   ![Inserir Print 1](C:\Users\Luiz Gustavo\Desktop\menu.png)

2. Cadastro de Médico:  
   ![Inserir Print 2](caminho/do/print2.png)

3. Relatório de ?:  
   ![Inserir Print 3](caminho/do/print3.png)

---

---

## Observações (Extras ou Dificuldades)

    Bem, encontrei muitos desafios nesse projeto. Primeiramente fiquei perdido no inicio, pois não fazia como funcionava a linguagem java e ainda mais para orientação a objetos. Então na primeira semana eu tava tentando entender melho como funcionava atributos, métodos e objetos de uma forma geral, com vídeos no youtube. Então eu ia aplicando devagar para ir entendendo na prática e saber como usaria no projeto. Fiquei perdido com git e com gitHub também, nunca tinha usado. Aos poucos o projeto foi criando forma e algumas ideias que eu tive foram boas e outras nem tanto. Acho que no final fico satisfeito com o resultado. Foi ótimo para aprender.

---

## Contato

- luiz22gcs@gmail.com

---

### 🖥️ Descrição do Sistema  

O sistema deve simular o funcionamento de um hospital com cadastro de *pacientes, médicos, especialidades, consultas e internações*.  

1. *Cadastro de hospital.entidade.Pacientes*  
   - hospital.entidade.Pacientes comuns e pacientes especiais (ex: com plano de saúde).  
   - Cada paciente deve ter: nome, CPF, idade, histórico de consultas e internações.  

2. *Cadastro de Médicos*  
   - Médicos podem ter especialidades (ex: cardiologia, pediatria, ortopedia).  
   - Cada médico deve ter: nome, CRM, especialidade, custo da consulta e agenda de horários.  

3. *hospital.servico.Agendamento de Consultas*  
   - Um paciente pode agendar uma consulta com um médico disponível.  
   - Consultas devem registrar: paciente, médico, data/hora, local, status (agendada, concluída, cancelada).  
   - hospital.entidade.Pacientes especiais (plano de saúde) podem ter *vantagens*, como desconto.  
   - Duas consultas não podem estar agendadas com o mesmo médico na mesma hora, ou no mesmo local e hora

4. *Consultas e Diagnósticos*  
   - Ao concluir uma consulta, o médico pode registrar *diagnóstico* e/ou *prescrição de medicamentos*.  
   - Cada consulta deve ser registrada no *histórico do paciente*.  

5. *Internações*  
   - hospital.entidade.Pacientes podem ser internados.  
   - Registrar: paciente, médico responsável, data de entrada, data de saída (se já liberado), quarto e custo da internação.  
   - Deve existir controle de *ocupação dos quartos* (não permitir duas internações no mesmo quarto simultaneamente).  
   - Internações devem poder ser canceladas, quando isso ocorrer, o sistema deve ser atualizado automaticamente.

6. *Planos de saúde*    
   -  Planos de saude podem ser cadastrados.
   -  Cada plano pode oferecer *descontos* para *especializações* diferentes, com possibilidade de descontos variados.
   -  Um paciente que tenha o plano de saúde deve ter o desconto aplicado.
   -  Deve existir a possibilidade de um plano *especial* que torna internação de menos de uma semana de duração gratuita.
   -  hospital.entidade.Pacientes com 60+ anos de idade devem ter descontos diferentes.

7. *Relatórios*  
   - hospital.entidade.Pacientes cadastrados (com histórico de consultas e internações).  
   - Médicos cadastrados (com agenda e número de consultas realizadas).  
   - Consultas futuras e passadas (com filtros por paciente, médico ou especialidade).  
   - hospital.entidade.Pacientes internados no momento (com tempo de internação).  
   - Estatísticas gerais (ex: médico que mais atendeu, especialidade mais procurada).  
   - Quantidade de pessoas em um determinado plano de saúde e quanto aquele plano *economizou* das pessoas que o usam.  


---

### ⚙️ Requisitos Técnicos  
- O sistema deve ser implementado em *Java*.  
- Interface via *terminal (linha de comando)*.  
- Os dados devem ser persistidos em *arquivos* (.txt ou .csv).  
- Deve existir *menu interativo*, permitindo navegar entre as opções principais.  

---

### 📊 Critérios de Avaliação  

1. *Modos da Aplicação (1,5)* → Cadastro de pacientes, médicos, planos de saúde, consultas e internações.  
2. *Armazenamento em arquivo (1,0)* → Dados persistidos corretamente, leitura e escrita funcional.  
3. *Herança (1,0)* → Ex.: Paciente e PacienteEspecial, hospital.entidade.Consulta e ConsultaEspecial, Médico e subclasses por especialidade.  
4. *Polimorfismo (1,0)* → Ex.: regras diferentes para agendamento, preços de consultas.
5. *Encapsulamento (1,0)* → Atributos privados, getters e setters adequados.  
6. *Modelagem (1,0)* → Estrutura de classes clara, bem planejada e com relacionamentos consistentes.  
7. *Execução (0,5)* → Sistema compila, roda sem erros e possui menus funcionais.  
8. *Qualidade do Código (1,0)* → Código limpo, organizado, nomes adequados e boas práticas.  
9. *Repositório (1,0)* → Uso adequado de versionamento, commits frequentes com mensagens claras.  
10. *README (1,0)* → Vídeo curto (máx. 5 min) demonstrando as funcionalidades + prints de execução + explicação da modelagem.  

🔹 *Total = 10 pontos*  
🔹 *Pontuação extra (até 1,5)* → Melhorias relevantes, como:  
- Sistema de triagem automática com fila de prioridade.  
- Estatísticas avançadas (tempo médio de internação, taxa de ocupação por especialidade).  
- Exportação de relatórios em formato .csv ou .pdf.  
- Implementação de testes unitários para classes principais.  
- Menu visual.
