package com.automatoplay.guis.tela_ambientes.automatos.telas_aux;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.automatoplay.controles.ambientes.automatos.Transicao;
import com.automatoplay.controles.ambientes.automatos.Estado;
import com.automatoplay.fachadas.GUI;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class DesenharSeta extends View
{
    public DesenharSeta(Context context) {
        super(context);
    }

    public DesenharSeta(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DesenharSeta(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DesenharSeta(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas c)
    {
        super.onDraw(c);
        desenharSetas(c);
    }

    public void redesenharSetas(){
        invalidate();
    }

    private int arrowLength = 20;
    private int arrowWidth = 15;
    private int strokeWidth = 3;

    private void desenharSetas(Canvas canvas){
        GUI.getGui().getTelaAmbiente2().limparTelaGradeSimbolos();
        GUI.getGui().getTelaAmbiente2().limparTelaGradeEstados();
        ArrayList<Estado> estados = GUI.getGui().getItemTelaDesenhoAutomato().getListaEstadosAutomato();
        for(int i = 0; i < estados.size(); i++){
            ArrayList<Transicao> transicoes = estados.get(i).getTransicoes();
            for(int j = 0; j < transicoes.size(); j++) {
                Transicao transicao = transicoes.get(j);
                if(transicao.getCordenadas_seta()[0] != transicao.getCordenadas_seta()[2] || transicao.getCordenadas_seta()[1] != transicao.getCordenadas_seta()[3]) {
                    montarSeta(canvas, transicao.getCordenadas_seta()[0], transicao.getCordenadas_seta()[1], transicao.getCordenadas_seta()[2],
                            transicao.getCordenadas_seta()[3], transicao, estados.get(i));
                }
                else{
                    GUI.getGui().getTelaAmbiente2().desenharSetaMesmoAutomato(estados.get(i), transicao);
                }
            }
        }
    }

    private int verificarSetaDupla(Transicao transicao){
        int resultado = 2;
        ArrayList<Estado> estados = GUI.getGui().getItemTelaDesenhoAutomato().getListaEstadosAutomato();
        for(int i = 0; i < estados.size(); i++){
            ArrayList<Transicao> transicoes = estados.get(i).getTransicoes();
            for(int j = 0; j < transicoes.size(); j++) {
                Transicao transicao2 = transicoes.get(j);
                if(transicao == transicao2)
                    resultado = 1;
                if(transicao != transicao2 && transicao.getCordenadas_seta()[0] == transicao2.getCordenadas_seta()[2] &&
                        transicao.getCordenadas_seta()[1] == transicao2.getCordenadas_seta()[3] &&
                        transicao.getCordenadas_seta()[2] == transicao2.getCordenadas_seta()[0] &&
                        transicao.getCordenadas_seta()[3] == transicao2.getCordenadas_seta()[1])
                    return resultado;
            }
        }
        return 0;
    }

    private void montarSeta(Canvas canvas, float startX, float startY, float mX, float mY, Transicao transicao, Estado estado) {

        // startX e startY é as coordenadas do estado de saída, eles apontam para a diagonal superior esquerdo do quadrado que o estado está
        // mX e mY é as coordenadas do estado de chegada, eles apontam para a diagonal superior esquerdo do quadrado que o estado está

        // calcula o angulo da seta
        double angle = calculateAngle(startX, startY, mX, mY);

        float final_angle = (float) (180 - angle);

        Path arrow_path = new Path();

        // Soma cada uma das coordenadas com a metade do tamanho do quadrado que o estado está inserido, assim as coordenadas apontam para o meio do quadrado
        startX = (startX + GUI.getGui().getItemTelaDesenhoAutomato().getWidth()/2);
        startY = (startY + GUI.getGui().getItemTelaDesenhoAutomato().getHeiht()/2);
        mX = (mX + GUI.getGui().getItemTelaDesenhoAutomato().getWidth()/2);
        mY = mY + (GUI.getGui().getItemTelaDesenhoAutomato().getHeiht()/2);

        float cont1 = 0;
        float cont2 = 0;
        ArrayList<float[]> pontos = new ArrayList<>();
        pontos.add(new float[]{startX, startY});

        // começa a traçar uma linha pontilhada saindo de um estado para outro (linha da seta)

        for (int i = 0; true; i++) {

            // verifica se os dois estados estão no mesmo plano cartesiano X, se sim, ele vai traçar a linha verticalmente
            if(startX == mX) {
                float y = 0;
                // verifica se o estado de chegada está abaixo do estado de saida
                if(mY > startY)
                    y = startY + i;
                else // o estado de chegada está acima do estado de saida
                    y = startY - i;
                pontos.add(new float[]{startX, y});
                if(y == Math.round(mY)) // verifica se a linha verticalmente já chegou no estado de chegada
                    break;
            }
            // verifica se os dois estados estão no mesmo plano cartesiano de Y, se sim, ele vai traçar a linha horizontalmente
            else if(startY == mY) {
                float x = 0;
                // verifica se o estado de chegada está a direita do estado de saida
                if(mX > startX)
                    x = startX + i;
                else // o estado de chegada está a esquerda do estado de saida
                    x = startX - i;
                pontos.add(new float[]{x, startY});
                if(x == Math.round(mX)) // verifica se a linha horizontalmente já chegou no estado de chegada
                    break;
            }
            else { // a linha é inclinada
                if(mX > startX) { // verifica se o estado de chegada está a direita do estádo de saida
                    float d = (mY - startY)/(mX - startX);
                    cont1++;
                    cont2 += d;
                }
                else{ // o estado de chegada está a esquerda do estado de saida
                    if(mY > startY) { // o estado de chegada está abaixo do estado de saida
                        float d = (mY - startY) / (startX - mX);
                        cont1--;
                        cont2 += d;
                    }
                    else{ // o estado de chegada está acima do estádo de saida
                        float d = (startY - mY)/(startX - mX);
                        cont1--;
                        cont2 -= d;
                    }
                }
                pontos.add(new float[]{startX + cont1, startY + cont2});
                if (startY + cont2 == mY || startX + cont1 == mX) // verifica se a linha chegou no estado de chegada
                    break;
            }
        }

        double r = GUI.getGui().getItemTelaDesenhoAutomato().getWidth() / 2;
        double r2 = r * r;
        int inicio = 0;
        int fim = pontos.size() - 1;

        for (int i = 0; i < pontos.size(); i++) {
            double dx = pontos.get(i)[0] - startX;
            double dy = pontos.get(i)[1] - startY;

            double dd = (dx * dx) + (dy * dy);

            if (dd >= r2) {
                inicio = i;
                break;
            }
        }

        for (int i = pontos.size() - 1; i >= 0; i--) {
            double dx = pontos.get(i)[0] - mX;
            double dy = pontos.get(i)[1] - mY;

            double dd = (dx * dx) + (dy * dy);

            if (dd >= r2) {
                fim = i;
                break;
            }
        }

        float xa = startX;
        float ya = startY;
        float xb = mX;
        float yb = mY;

        startX = pontos.get(inicio)[0];
        startY = pontos.get(inicio)[1];
        mX = pontos.get(fim)[0];
        mY = pontos.get(fim)[1];

        ArrayList<float[]> pontosAux = new ArrayList<>();
        for(int i = inicio; i < fim; i++) {
            pontosAux.add(pontos.get(i));
        }

        int resultado = verificarSetaDupla(transicao);
        int[] cor =  new int[]{0,0,0};
        if(resultado == 0) {
            canvas.drawLine(startX, startY, mX, mY, initPaint(cor));
            transicao.setCordenadas_simbolos(new float[]{pontosAux.get((int)pontosAux.size()/2)[0], pontosAux.get((int)pontosAux.size()/2)[1]});
        }else{
            float x0 = xa, y0 = ya, x1 = xb, y1 = yb;

            double dx = x1 - x0, dy = y1 - y0;
            double R = Math.sqrt(dx * dx + dy * dy);
            double cc = r / R;
            double angulo = (Math.PI / 4);

            int sinal = -1;

            double cos = Math.cos(sinal * angulo);
            double sen = Math.sin(sinal * angulo);
            float xinicio = x0, yinicio = y0, xfim = x1, yfim = y1;
            xinicio = (int) Math.round((dx * cc * cos - dy * cc * sen) + x0);
            yinicio = (int) Math.round((dx * cc * sen + dy * cc * cos) + y0);

            cos = Math.cos(-sinal * angulo + Math.PI);
            sen = Math.sin(-sinal * angulo + Math.PI);
            xfim = (int) Math.round((dx * cc * cos - dy * cc * sen) + x1);
            yfim = (int) Math.round((dx * cc * sen + dy * cc * cos) + y1);

            startX = xinicio;
            startY = yinicio;
            mX = xfim;
            mY = yfim;

            Paint paint  = new Paint();
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(strokeWidth);
            Path path = new Path();
            float midX            = startX + ((mX - startX) / 2);
            float midY            = startY + ((mY - startY) / 2);
            float xDiff         = midX - startX;
            float yDiff         = midY - startY;
            double angl        = (Math.atan2(yDiff, xDiff) * (180 / Math.PI)) - 90;
            double angleRadians = Math.toRadians(angl);
            float pointX = (float) (midX + 0 * Math.cos(angleRadians));
            float pointY = (float) (midY + 0 * Math.sin(angleRadians));
            path.moveTo(startX, startY);
            path.cubicTo(startX,startY,pointX, pointY, mX, mY);

            if(resultado == 1) {
                transicao.setCordenadas_simbolos(new float[]{pointX, pointY});
            }
            else{
                transicao.setCordenadas_simbolos(new float[]{pointX, pointY});
            }

            paint.setColor(new Color().rgb(cor[0], cor[1], cor[2]));
            canvas.drawPath(path, paint);
        }

        GUI.getGui().getTelaAmbiente2().desenharSimbolosSeta(estado, transicao, cor);

        Matrix arrow_matrix = new Matrix();

        arrow_matrix.postRotate(final_angle, mX, mY);

        arrow_path.moveTo(mX, mY);
        arrow_path.lineTo(mX + arrowWidth, mY + arrowLength);
        arrow_path.moveTo(mX, mY);
        arrow_path.lineTo(mX - (arrowWidth), mY + arrowLength);
        arrow_path.transform(arrow_matrix);

        canvas.drawPath(arrow_path, initPaint(cor));
    }
    private int mod100(float y){
        int resultado = 1;

        return 1;
    }
    private boolean verificarNumeroInteiro(float numero){
        String n = Float.toString(numero).replace(".", ",");
        String[] n2 = n.split(",");
        try{
            if((Integer.parseInt(n2[1])) == 0)
                return true;
            else
                return false;
        }catch (Exception e){
            return true;
        }
    }
    private Paint initPaint(int[] cor) {
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(new Color().rgb(cor[0], cor[1], cor[2]));
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(strokeWidth);
        return mPaint;
    }
    private double calculateAngle(double x1, double y1, double x2, double y2) {
        double angle = Math.toDegrees(Math.atan2(x2 - x1, y2 - y1));

        angle = angle + Math.ceil(-angle / 360) * 360; //Keep angle between 0 and 360

        return angle;
    }

}


