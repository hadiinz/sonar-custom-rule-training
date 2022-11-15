package org.sonar.samples.java.checks;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.*;

import java.util.Collections;
import java.util.List;

@Rule(key = "EntityManagerCustomRule")
public class EntityManagerCheck extends IssuableSubscriptionVisitor {
    @Override
    public List<Tree.Kind> nodesToVisit() {
        return Collections.singletonList(Tree.Kind.CLASS);
    }

    @Override
    public void visitNode(Tree tree) {
        ClassTree classTree = (ClassTree) tree;

        List<Tree> members = classTree.members();
        members.stream().filter(member -> member.is(Tree.Kind.VARIABLE))
                .map((VariableTree.class::cast))
                .filter(variable -> variable.type().symbolType().is("EntityManager") && !classTree.simpleName().toString().contains("Dao"))
                .forEach(a -> reportIssue(classTree.simpleName(), "Never do that!"));


    }
}
