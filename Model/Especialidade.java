// enum de especialidades puxando aquela interface pra cada especialidade com restrição
public enum Especialidade implements EspecialidadeRestricao{
    // Especialidades COM restrições
    PEDIATRIA("Pediatria") {
        @Override
        public boolean isAdequadaPara(Integer idade, Genero genero) {
            return idade >= 0 && idade <= 18; // Apenas crianças e adolescentes
        }
    },
    
    CIRURGIA_PEDIATRICA("Cirurgia pediátrica"){
        @Override
        public boolean isAdequadaPara(Integer idade, Genero genero) {
            return idade >= 0 && idade <= 18; // Apenas crianças e adolescentes
        }
    },

    GINECOLOGIA_OBSTETRICIA("Ginecologia e Obstetricia") {
        @Override
        public boolean isAdequadaPara(Integer idade, Genero genero) {
            return genero == Genero.FEMININO; // Mulheres
        }
    },

    MASTOLOGIA("Mastologia") {
        @Override
        public boolean isAdequadaPara(Integer idade, Genero genero) {
            return genero == Genero.FEMININO; // Mulheres
        }
        // Mastologia é uma subespecialidade da ginecologia, então restrita a mulheres
    },
    
    GERIATRIA("Geriatria") {
        @Override
        public boolean isAdequadaPara(Integer idade, Genero genero) {
            return idade >= 65; // Idosos
        }
    },
    
    // Especialidades SEM restrições
    ACUPUNTURA("Acupuntura"),
    ALERGIA_E_IMUNOLOGIA("Alergia e imunologia"),
    ANESTESIOLOGIA("Anestesiologia"),
    ANGIOLOGIA("Angiologia"),
    CARDIOLOGIA("Cardiologia"),
    CIRURGIA_CARDIOVASCULAR("Cirurgia cardiovascular"),
    CIRURGIA_DA_MAO("Cirurgia da mão"),
    CIRURGIA_DE_CABECA_E_PESCOCO("Cirurgia de cabeça e pescoço"),
    CIRURGIA_DO_APARELHO_DIGESTIVO("Cirurgia do aparelho digestivo"),
    CIRURGIA_GERAL("Cirurgia geral"),
    CIRURGIA_ONCOLOGICA("Cirurgia oncológica"),
    CIRURGIA_PLASTICA("Cirurgia plástica"),
    CIRURGIA_TORACICA("Cirurgia torácica"),
    CIRURGIA_VASCULAR("Cirurgia vascular"),
    CLINICA_MEDICA("Clínica médica"),
    COLO_PROCTOLOGIA("Coloproctologia"),
    DERMATOLOGIA("Dermatologia"),
    ENDOCRINOLOGIA_E_METABOLOGIA("Endocrinologia e metabologia"),
    ENDOSCOPIA("Endoscopia"),
    GASTROENTEROLOGIA("Gastroenterologia"),
    GENETICA_MEDICA("Genética médica"),
    HEMATOLOGIA_E_HEMOTERAPIA("Hematologia e hemoterapia"),
    HOMEOPATIA("Homeopatia"),
    INFECTOLOGIA("Infectologia"), 
    MEDICINA_DE_EMERGENCIA("Medicina de emergência"),
    MEDICINA_DE_FAMILIA_E_COMUNIDADE("Medicina de família e comunidade"),
    MEDICINA_DO_TRABALHO("Medicina do trabalho"),
    MEDICINA_DO_TRAFEGO("Medicina do tráfego"),
    MEDICINA_ESPORTIVA("Medicina esportiva"),
    MEDICINA_FISICA_E_REABILITACAO("Medicina física e reabilitação"),
    MEDICINA_INTENSIVA("Medicina intensiva"),
    MEDICINA_LEGAL_E_PERICIA_MEDICA("Medicina legal e perícia médica"),
    MEDICINA_NUCLEAR("Medicina nuclear"),
    MEDICINA_PREVENTIVA_E_SOCIAL("Medicina preventiva e social"),
    NEFROLOGIA("Nefrologia"),
    NEUROCIRURGIA("Neurocirurgia"),
    NEUROLOGIA("Neurologia"),
    NUTROLOGIA("Nutrologia"),
    OFTALMOLOGIA("Oftalmologia"),
    ONCOLOGIA_CLINICA("Oncologia clínica"),
    ORTOPEDIA_E_TRAUMATOLOGIA("Ortopedia e traumatologia"),
    OTORRINOLARINGOLOGIA("Otorrinolaringologia"),
    PATOLOGIA("Patologia"),
    PATOLOGIA_CLINICA_MEDICINA_LABORATORIAL("Patologia clínica/medicina laboratorial"),
    PNEUMOLOGIA("Pneumologia"),
    PSIQUIATRIA("Psiquiatria"),
    RADIOLOGIA_E_DIAGNOSTICO_POR_IMAGEM("Radiologia e diagnóstico por imagem"),
    RADIOTERAPIA("Radioterapia"),
    REUMATOLOGIA("Reumatologia"),
    UROLOGIA("Urologia");

    private final String nomeExibicao;

    Especialidade(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    // Implementação padrão para especialidades sem restrições
    @Override
    public boolean isAdequadaPara(Integer idade, Genero genero) {
        return true; // Sem restrições
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }
}