package com.example.physicsmate.activities;

import org.junit.Test;
import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;

public class airResistanceDE
{
    //NDSolve({x'(t) == 10*(y(t) - x(t)), y'(t) == x(t)*(28 - z(t)) - y(t), z'(t) == x(t)*y(t) - 8/3*z(t), x(0)== 0, y(0) == 1, z(0) == 0}, {x, y, z}, {t, 0, 20});

    @Test
    public void DE1() {
        ExprEvaluator evaluator = new ExprEvaluator();
        IExpr model = (evaluator.eval("NDSolve({u==1, g==9.8,x'(t)==vx(t), y'(t) == vy(t), vx'(t)== -u*vx(t)*(vx(t)^2+vy(t)^2)^(1/2), vy'(t)== -g-u*vy(t)*(vx(t)^2+vy(t)^2)^(1/2), x(0)==0, y(0)==0, vx(0) == 20, vy(0) == 10}, {x,y,vx,vy}, {t,0,1})"));
        System.out.println(model.toString());
    }
}
