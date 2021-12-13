package com.lind.start.test;

import com.lind.common.jackson.convert.EnableJacksonFormatting;
import lombok.SneakyThrows;
import org.aesh.command.Command;
import org.aesh.command.CommandDefinition;
import org.aesh.command.CommandException;
import org.aesh.command.CommandResult;
import org.aesh.command.invocation.CommandInvocation;
import org.aesh.command.option.Option;
import org.aesh.command.registry.CommandRegistryException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
//@EnableRunTime
@EnableJacksonFormatting
public class TestApplication {
  @SneakyThrows
  public static void main(String[] args) throws CommandRegistryException, IOException {
    SpringApplication.run(TestApplication.class, args);
    // 命令行输入
    // AeshRuntimeRunner.builder().command(AddUserCommand.class).args(args).execute();
//    CommandRegistry registry = AeshCommandRegistryBuilder.builder()
//        .command(AddUserCommand.class)
//        .command(ExitCommand.class)
//        .create();
//
//    Settings settings = SettingsBuilder
//        .builder()
//        .commandRegistry(registry)
//        .build();
//    ReadlineConsole console = new ReadlineConsole(settings);
//    console.setPrompt("[lind@aesh]$ ");
//    console.start();


  }


  /**
   * 命令行工具
   * 使用：[lind@aesh] add-user -r="master" -u="admin"
   */
  @CommandDefinition(name = "add-user", description = "[options...]")
  public static class AddUserCommand implements Command {

    @Option(shortName = 'r', hasValue = true, description = "Name of realm to add user to")
    private String realm;

    @Option(shortName = 'u', hasValue = true, description = "Name of the user")
    private String user;


    @Override
    public CommandResult execute(CommandInvocation commandInvocation) throws InterruptedException {
      System.out.println("user=" + user);
      return CommandResult.SUCCESS;
    }
  }


  @CommandDefinition(name = "exit", description = "exit the program", aliases = {"quit"})
  public static class ExitCommand implements Command {
    @Override
    public CommandResult execute(CommandInvocation commandInvocation) throws CommandException, InterruptedException {
      commandInvocation.stop();
      return CommandResult.SUCCESS;
    }
  }

}
