
/* Ecrit un programme java qui permettra d'encoder et de gérer les côtes finales des étudiants pour un cours basé sur deux interrogations (quivalent pour 25% chacune) et un examen final ce   programme java devra offrir:

1. Permettre au responsable de la saisie d'encoder les 3 côtes d'un cours pour un étudiant et d'afficher la côte finale obtenue. La première côte encodée sera toujours celle de l'examen. Les 3 côtes ainsi que la côte finale doivent être compris dans l'intervalle (0, 20] est sont des nombres entiers. Par exemple, si l'étudiant A obtient 14/20 à l'examen, 16/20 à la première interrogation et 12/20 à la deuxième interrogation, le programme affichera "Côte finale obtenue: 14/20". (0.50 14 +0.25 16+0.25" 12-14).

2. Une fois l'encodage des côtes d'un étudiant terminé, le programme proposera au responsable d'encoder la côte d'un autre étudiant sans avoir à relancer le programme. Le responsable aura la possibilité de signaler la fin de la saisie côtes pour terminer l'encodage. Après chaque étudiant, les valeurs individuelles des 3 côtes de cet étudiant ne doivent pas être mémorisées.

3. A la fin de l'encodage des côtes (lorsque le responsable indique qu'il n'y a plus d'étudiants à encoder), le programme affichera la moyenne, le minimum et le maximum des côtes finales obtenues par tous les étudiants.

4. Permettre au responsable, à la fin de l'encodage des côtes, s'il le souhaite, de demander au programme la côte finale totale obtenue par un étudiant spécifique (numéroté de 1 à 42). Le programme ne retiendra que les côtes des 42 premiers étudiants. Un message d'erreur doit être affiché dans la console si la côte finale d'un étudiant non existant est demandée. Le superviseur peut consulter les côtes finales autant de fois qu'il le souhaite. Le programme lui proposera après chaque consultation d'encoder un charactère/numéro spécifique afin de terminer la consultation.

Pour aller au-delà du 10/20 (assurez-vous que votre programme de la première partie marche avant de commencer celle-ci):

A) À la fin de l'encodage des côtes, afficher le pourcentage de réussite global des étudiants. Pour réussir, l'étudiant doit avoir obtenu une côte finale supérieure ou égale à la côte de passage qui est de 10 par défaut.

B) Permettre au responsable, s'il le souhaite, avant d'encoder les résultats, d'encoder lui-même la côte de passage (par exemple, 12/20). Les étudiants qui ont obtenu une côte finale supérieure ou égale à celle-ci seront considérés comme ayant réussi le cours.

C) En plus des 3 côtes de l'étudiant, demander au responsable d'encoder le nombre de jours d'absence non justifiés pour l'étudiant. Si le nombre de jours est supérieur à 1, retirer 2 points par jour d'absence en trop de la côte finale pour l'étudiant concerné. (Retirer 2 si 2 jours d'absence, 4 si 3, 6 si 4 etc.)

Assurez-vous de mettre en place une interface conviviale dans la console pour que le responsable puisse interagir facilement avec le programme et saisir les informations relatives aux étudiants et au cours. */
import java.util.Scanner;

public class index {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int numberOfStudents = 0;
        int[] examGrades = new int[42];
        int[] int1Grades = new int[42];
        int[] int2Grades = new int[42];
        int[] absences = new int[42];
        
        int passingGrade = 10;
        double[] weights = {0.25, 0.25, 0.5};
        
        double totalFinalGrade = 0;
        int minFinalGrade = 20;
        int maxFinalGrade = 0;
        int passedStudents = 0;

        while (true) {
            System.out.println("Options:\n1. Encoder les côtes d'un étudiant\n2. Quitter");
            int choice = scanner.nextInt();
            
            if (choice == 1) {
                numberOfStudents++;
                System.out.println("Côte examen (0-20) : ");
                examGrades[numberOfStudents - 1] = scanner.nextInt();
                System.out.println("Côte interrogation 1 (0-20) : ");
                int1Grades[numberOfStudents - 1] = scanner.nextInt();
                System.out.println("Côte interrogation 2 (0-20) : ");
                int2Grades[numberOfStudents - 1] = scanner.nextInt();
                System.out.println("Nombre de jours d'absence non justifiés : ");
                absences[numberOfStudents - 1] = scanner.nextInt();
                
                int finalGrade = calculateFinalGrade(examGrades[numberOfStudents - 1], int1Grades[numberOfStudents - 1], int2Grades[numberOfStudents - 1], absences[numberOfStudents - 1], weights);
                totalFinalGrade += finalGrade;
                minFinalGrade = Math.min(minFinalGrade, finalGrade);
                maxFinalGrade = Math.max(maxFinalGrade, finalGrade);
                
                if (finalGrade >= passingGrade) {
                    passedStudents++;
                }
                
                System.out.println("Côte finale obtenue : " + finalGrade + "/20");
            } else if (choice == 2) {
                double averageFinalGrade = totalFinalGrade / numberOfStudents;
                double successRate = (double) passedStudents / numberOfStudents * 100;
                
                System.out.println("Moyenne des côtes finales : " + averageFinalGrade);
                System.out.println("Côte finale minimale : " + minFinalGrade);
                System.out.println("Côte finale maximale : " + maxFinalGrade);
                System.out.println("Taux de réussite : " + successRate + "%");
                
                System.out.println("Voulez-vous consulter la côte finale d'un étudiant spécifique ? (O/N)");
                String consultChoice = scanner.next();
                
                if (consultChoice.equalsIgnoreCase("O")) {
                    System.out.println("Entrez le numéro de l'étudiant (1-42) : ");
                    int studentNumber = scanner.nextInt();
                    
                    if (studentNumber >= 1 && studentNumber <= numberOfStudents) {
                        System.out.println("Côte finale de l'étudiant " + studentNumber + " : " + calculateFinalGrade(examGrades[studentNumber - 1], int1Grades[studentNumber - 1], int2Grades[studentNumber - 1], absences[studentNumber - 1], weights) + "/20");
                    } else {
                        System.out.println("Étudiant non trouvé.");
                    }
                }
                
                break;
            }
        }
    }
    
    public static int calculateFinalGrade(int examGrade, int int1Grade, int int2Grade, int absences, double[] weights) {
        int finalGrade = (int) Math.round((weights[0] * examGrade) + (weights[1] * int1Grade) + (weights[2] * int2Grade));
        if (absences > 0) {
            finalGrade -= 2 * absences;
            finalGrade = Math.max(finalGrade, 0);
        }
        return finalGrade;
    }
}

/*/* 
deuxieme methode 
import java.util.Scanner;

public class GestionCotesEtudiants {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfStudents = 0;
        int[] examGrades = new int[42];
        int[] int1Grades = new int[42];
        int[] int2Grades = new int[42];
        int[] absences = new int[42];
        int passingGrade = 10;

        while (true) {
            System.out.println("Options:\n1. Encoder les côtes d'un étudiant\n2. Quitter");
            int choice = scanner.nextInt();

            if (choice == 1) {
                numberOfStudents++;

                System.out.println("Entrez les côtes de l'étudiant " + numberOfStudents + ":");
                examGrades[numberOfStudents - 1] = getValidGrade("Examen");
                int1Grades[numberOfStudents - 1] = getValidGrade("Interrogation 1");
                int2Grades[numberOfStudents - 1] = getValidGrade("Interrogation 2");
                absences[numberOfStudents - 1] = getAbsences();

                int finalGrade = calculateFinalGrade(examGrades[numberOfStudents - 1], int1Grades[numberOfStudents - 1], int2Grades[numberOfStudents - 1], absences[numberOfStudents - 1]);
                System.out.println("Côte finale obtenue : " + finalGrade + "/20");
            } else if (choice == 2) {
                int totalFinalGrade = 0;
                int minFinalGrade = Integer.MAX_VALUE;
                int maxFinalGrade = Integer.MIN_VALUE;
                int passedStudents = 0;

                for (int i = 0; i < numberOfStudents; i++) {
                    int finalGrade = calculateFinalGrade(examGrades[i], int1Grades[i], int2Grades[i], absences[i]);
                    totalFinalGrade += finalGrade;
                    minFinalGrade = Math.min(minFinalGrade, finalGrade);
                    maxFinalGrade = Math.max(maxFinalGrade, finalGrade);

                    if (finalGrade >= passingGrade) {
                        passedStudents++;
                    }
                }

                double averageFinalGrade = (double) totalFinalGrade / numberOfStudents;
                double successRate = (double) passedStudents / numberOfStudents * 100;

                System.out.println("Moyenne des côtes finales : " + averageFinalGrade);
                System.out.println("Côte finale minimale : " + minFinalGrade);
                System.out.println("Côte finale maximale : " + maxFinalGrade);
                System.out.println("Taux de réussite : " + successRate + "%");
                break;
            }
        }
    }

    public static int getValidGrade(String gradeName) {
        Scanner scanner = new Scanner(System.in);
        int grade;
        do {
            System.out.print("Côte " + gradeName + " (0-20) : ");
            grade = scanner.nextInt();
        } while (grade < 0 || grade > 20);
        return grade;
    }

    public static int getAbsences() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre de jours d'absence non justifiés : ");
        return scanner.nextInt();
    }

    public static int calculateFinalGrade(int examGrade, int int1Grade, int int2Grade, int absences) {
        double weightedExamGrade = examGrade * 0.5;
        double weightedInt1Grade = int1Grade * 0.25;
        double weightedInt2Grade = int2Grade * 0.25;
        int finalGrade = (int) Math.round(weightedExamGrade + weightedInt1Grade + weightedInt2Grade);
        
        if (absences > 0) {
            int pointsToDeduct = absences * 2;
            finalGrade = Math.max(0, finalGrade - pointsToDeduct);
        }

        return finalGrade;
    }
}
*/ 
