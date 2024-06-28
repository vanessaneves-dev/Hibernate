package ProjetoEmpresa;

import ProjetoEmpresa.DAO.DepartamentoDAO;
import ProjetoEmpresa.DAO.FuncionarioDAO;
import ProjetoEmpresa.DAO.ProjetoDAO;
import ProjetoEmpresa.Model.Departamento;
import ProjetoEmpresa.Model.Funcionario;
import ProjetoEmpresa.Model.Projeto;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private static DepartamentoDAO departamentoDAO = new DepartamentoDAO();
    private static ProjetoDAO projetoDAO = new ProjetoDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("1. Adicionar Funcionario");
            System.out.println("2. Adicionar Departamento");
            System.out.println("3. Adicionar Projeto");
            System.out.println("4. Listar Funcionarios");
            System.out.println("5. Listar Departamentos");
            System.out.println("6. Listar Projetos");
            System.out.println("7. Atualizar Funcionario");
            System.out.println("8. Atualizar Departamento");
            System.out.println("9. Atualizar Projeto");
            System.out.println("10. Excluir Funcionario");
            System.out.println("11. Excluir Departamento");
            System.out.println("12. Excluir Projeto");
            System.out.println("13. Funcionarios por Departamento");
            System.out.println("14. Funcionarios por Projeto");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> addFuncionario(scanner);
                case 2 -> addDepartamento(scanner);
                case 3 -> addProjeto(scanner);
                case 4 -> listFuncionarios();
                case 5 -> listDepartamentos();
                case 6 -> listProjetos();
                case 7 -> updateFuncionario(scanner);
                case 8 -> updateDepartamento(scanner);
                case 9 -> updateProjeto(scanner);
                case 10 -> deleteFuncionario(scanner);
                case 11 -> deleteDepartamento(scanner);
                case 12 -> deleteProjeto(scanner);
                case 13 -> listFuncionariosByDepartamento(scanner);
                case 14 -> listFuncionariosByProjeto(scanner);
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (option != 0);
    }

    private static void addFuncionario(Scanner scanner) {
        System.out.print("Nome do Funcionario: ");
        String nome = scanner.next();
        System.out.print("Cargo do Funcionario: ");
        String cargo = scanner.next();
        System.out.print("ID do Departamento: ");
        Long departamentoId = scanner.nextLong();
        System.out.print("ID do Projeto: ");
        Long projetoId = scanner.nextLong();

        Departamento departamento = departamentoDAO.find(departamentoId);
        Projeto projeto = projetoDAO.find(projetoId);

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCargo(cargo);
        funcionario.setDepartamento(departamento);
        funcionario.setProjeto(projeto);

        funcionarioDAO.save(funcionario);
        System.out.println("Funcionario adicionado com sucesso!");
    }

    private static void addDepartamento(Scanner scanner) {
        System.out.print("Nome do Departamento: ");
        String nome = scanner.next();

        Departamento departamento = new Departamento();
        departamento.setNome(nome);

        departamentoDAO.save(departamento);
        System.out.println("Departamento adicionado com sucesso!");
    }

    private static void addProjeto(Scanner scanner) {
        System.out.print("Nome do Projeto: ");
        String nome = scanner.next();

        Projeto projeto = new Projeto();
        projeto.setNome(nome);

        projetoDAO.save(projeto);
        System.out.println("Projeto adicionado com sucesso!");
    }

    private static void listFuncionarios() {
        List<Funcionario> funcionarios = funcionarioDAO.findAll();
        for (Funcionario f : funcionarios) {
            System.out.println("ID: " + f.getId() + ", Nome: " + f.getNome() + ", Cargo: " + f.getCargo() +
                    ", Departamento: " + f.getDepartamento().getNome() + ", Projeto: " + f.getProjeto().getNome());
        }
    }

    private static void listDepartamentos() {
        List<Departamento> departamentos = departamentoDAO.findAll();
        for (Departamento d : departamentos) {
            System.out.println("ID: " + d.getId() + ", Nome: " + d.getNome());
        }
    }

    private static void listProjetos() {
        List<Projeto> projetos = projetoDAO.findAll();
        for (Projeto p : projetos) {
            System.out.println("ID: " + p.getId() + ", Nome: " + p.getNome());
        }
    }

    private static void updateFuncionario(Scanner scanner) {
        System.out.print("ID do Funcionario a ser atualizado: ");
        Long id = scanner.nextLong();
        Funcionario funcionario = funcionarioDAO.find(id);
        if (funcionario != null) {
            System.out.print("Novo Nome do Funcionario: ");
            String nome = scanner.next();
            System.out.print("Novo Cargo do Funcionario: ");
            String cargo = scanner.next();
            System.out.print("ID do novo Departamento: ");
            Long departamentoId = scanner.nextLong();
            System.out.print("ID do novo Projeto: ");
            Long projetoId = scanner.nextLong();

            Departamento departamento = departamentoDAO.find(departamentoId);
            Projeto projeto = projetoDAO.find(projetoId);

            funcionario.setNome(nome);
            funcionario.setCargo(cargo);
            funcionario.setDepartamento(departamento);
            funcionario.setProjeto(projeto);

            funcionarioDAO.update(funcionario);
            System.out.println("Funcionario atualizado com sucesso!");
        } else {
            System.out.println("Funcionario não encontrado!");
        }
    }

    private static void updateDepartamento(Scanner scanner) {
        System.out.print("ID do Departamento a ser atualizado: ");
        Long id = scanner.nextLong();
        Departamento departamento = departamentoDAO.find(id);
        if (departamento != null) {
            System.out.print("Novo Nome do Departamento: ");
            String nome = scanner.next();

            departamento.setNome(nome);
            departamentoDAO.update(departamento);
            System.out.println("Departamento atualizado com sucesso!");
        } else {
            System.out.println("Departamento não encontrado!");
        }
    }

    private static void updateProjeto(Scanner scanner) {
        System.out.print("ID do Projeto a ser atualizado: ");
        Long id = scanner.nextLong();
        Projeto projeto = projetoDAO.find(id);
        if (projeto != null) {
            System.out.print("Novo Nome do Projeto: ");
            String nome = scanner.next();

            projeto.setNome(nome);
            projetoDAO.update(projeto);
            System.out.println("Projeto atualizado com sucesso!");
        } else {
            System.out.println("Projeto não encontrado!");
        }
    }

    private static void deleteFuncionario(Scanner scanner) {
        System.out.print("ID do Funcionario a ser excluído: ");
        Long id = scanner.nextLong();
        funcionarioDAO.delete(id);
        System.out.println("Funcionario excluído com sucesso!");
    }

    private static void deleteDepartamento(Scanner scanner) {
        System.out.print("ID do Departamento a ser excluído: ");
        Long id = scanner.nextLong();
        departamentoDAO.delete(id);
        System.out.println("Departamento excluído com sucesso!");
    }

    private static void deleteProjeto(Scanner scanner) {
        System.out.print("ID do Projeto a ser excluído: ");
        Long id = scanner.nextLong();
        projetoDAO.delete(id);
        System.out.println("Projeto excluído com sucesso!");
    }

    private static void listFuncionariosByDepartamento(Scanner scanner) {
        System.out.print("ID do Departamento: ");
        Long departamentoId = scanner.nextLong();
        List<Funcionario> funcionarios = funcionarioDAO.findByDepartamento(departamentoId);
        for (Funcionario f : funcionarios) {
            System.out.println("ID: " + f.getId() + ", Nome: " + f.getNome() + ", Cargo: " + f.getCargo());
        }
    }

    private static void listFuncionariosByProjeto(Scanner scanner) {
        System.out.print("ID do Projeto: ");
        Long projetoId = scanner.nextLong();
        List<Funcionario> funcionarios = funcionarioDAO.findByProjeto(projetoId);
        for (Funcionario f : funcionarios) {
            System.out.println("ID: " + f.getId() + ", Nome: " + f.getNome() + ", Cargo: " + f.getCargo());
        }
    }
}