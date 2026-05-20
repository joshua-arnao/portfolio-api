package com.autodidacta.portafolioapi.shared.config;

import com.autodidacta.portafolioapi.experience.entity.Experience;
import com.autodidacta.portafolioapi.experience.repository.ExperienceRepository;
import com.autodidacta.portafolioapi.project.entity.Project;
import com.autodidacta.portafolioapi.project.entity.ProjectSection;
import com.autodidacta.portafolioapi.project.repository.ProjectRepository;
import com.autodidacta.portafolioapi.skill.entity.Skill;
import com.autodidacta.portafolioapi.skill.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;



@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final SkillRepository skillRepository;
    private final ExperienceRepository experienceRepository;
    private final ProjectRepository projectRepository;

    @Override
    public void run(String... args) {

        if (skillRepository.count() == 0) {
            skillRepository.saveAll(List.of(
                    Skill.builder().name("Java 17/21").type("tech&tools").build(),
                    Skill.builder().name("Spring Boot").type("tech&tools").build(),
                    Skill.builder().name("Spring AI").type("tech&tools").build(),
                    Skill.builder().name("Vertex AI").type("tech&tools").build(),
                    Skill.builder().name("Python").type("tech&tools").build(),
                    Skill.builder().name("REST APIs").type("tech&tools").build(),
                    Skill.builder().name("JWT · BCrypt").type("tech&tools").build(),
                    Skill.builder().name("PostgreSQL").type("tech&tools").build(),
                    Skill.builder().name("Docker").type("tech&tools").build(),
                    Skill.builder().name("Angular 17/18").type("frontend").build(),
                    Skill.builder().name("React").type("frontend").build(),
                    Skill.builder().name("TypeScript").type("frontend").build(),
                    Skill.builder().name("JavaScript ES6+").type("frontend").build(),
                    Skill.builder().name("Figma").type("productdesigner").build(),
                    Skill.builder().name("Design Thinking").type("productdesigner").build(),
                    Skill.builder().name("Lean UX").type("productdesigner").build(),
                    Skill.builder().name("Trabajo en equipo").type("softskill").build(),
                    Skill.builder().name("Comunicación efectiva").type("softskill").build()
            ));
        }

        if (experienceRepository.count() == 0) {
            experienceRepository.saveAll(List.of(
                    Experience.builder()
                            .type("work")
                            .year("Feb 2025 – Hoy")
                            .title("Bankiao — Fullstack Java Developer Jr")
                            .link("")
                            .build(),
                    Experience.builder()
                            .type("work")
                            .year("Ene 2020 – Hoy")
                            .title("Konecta Perú — Product Designer / UX Engineer")
                            .link("")
                            .build(),
                    Experience.builder()
                            .type("study")
                            .year("Ene 2026")
                            .title("Desarrollo de Aplicaciones Empresariales con Java — Cibertec")
                            .link("")
                            .build()
            ));
        }

        if (projectRepository.count() == 0) {
            projectRepository.saveAll(List.of(
                    Project.builder()
                            .title("Asistente financiero IA — Bankiao")
                            .description("Agente conversacional sobre datos financieros en tiempo real con Spring AI y Java 21.")
                            .imgCard("/assets/projects/bankiao-ai.png")
                            .featured(true)
                            .tools(List.of("Spring AI", "Java 21", "Spring Boot", "Virtual Threads", "JWT"))
                            .type(List.of("tech&tools"))
                            .link(List.of("https://github.com/joshua-arnao"))
                            .rol(List.of(
                                    "Diseñé módulos de billetera digital aplicando Domain-Driven Design",
                                    "Integré Spring AI con @Tool annotations para exponer lógica Java al LLM",
                                    "Configuré Virtual Threads de Java 21 para manejar concurrencia sin bloqueo"
                            ))
                            .problem(ProjectSection.builder()
                                    .description("Los usuarios necesitaban consultar datos financieros en lenguaje natural sin SQL ni dashboards.")
                                    .pareto(Map.of("1", "Alta concurrencia sin bloqueo de hilos", "2", "Consultas en tiempo real sobre datos financieros"))
                                    .list(Map.of())
                                    .images(Map.of())
                                    .build())
                            .understanding(ProjectSection.builder()
                                    .description("")
                                    .pareto(Map.of())
                                    .list(Map.of())
                                    .images(Map.of())
                                    .build())
                            .breacking(ProjectSection.builder()
                                    .description("")
                                    .pareto(Map.of())
                                    .list(Map.of())
                                    .images(Map.of())
                                    .build())
                            .overall(ProjectSection.builder()
                                    .description("Consultas en lenguaje natural sobre datos financieros reales con JWT stateless y Optimistic Locking.")
                                    .pareto(Map.of())
                                    .list(Map.of("1", "Race conditions eliminadas con @Version", "2", "Throughput mejorado con Virtual Threads"))
                                    .images(Map.of())
                                    .build())
                            .conclusions(ProjectSection.builder()
                                    .description("")
                                    .pareto(Map.of())
                                    .list(Map.of())
                                    .images(Map.of())
                                    .build())
                            .sortOrder(1)
                            .build()
            ));
        }
    }
}
