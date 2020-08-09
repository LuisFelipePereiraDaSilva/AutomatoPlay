package com.example.montanhadechomsky.guis.tela_ambientes.automatos.telas_aux;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.example.montanhadechomsky.controles.ambientes.automatos.Transicao;
import com.example.montanhadechomsky.controles.ambientes.automatos.Estado;
import com.example.montanhadechomsky.fachadas.GUI;

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

        double angle = calculateAngle(startX, startY, mX, mY);

        float final_angle = (float) (180 - angle);

        Path arrow_path = new Path();

        startX = (startX + GUI.getGui().getItemTelaDesenhoAutomato().getWidth()/2);
        startY = (startY + GUI.getGui().getItemTelaDesenhoAutomato().getHeiht()/2);
        mX = (mX + GUI.getGui().getItemTelaDesenhoAutomato().getWidth()/2);
        mY = mY + (GUI.getGui().getItemTelaDesenhoAutomato().getHeiht()/2);

        float cont1 = 0;
        float cont2 = 0;
        ArrayList<float[]> pontos = new ArrayList<>();
        pontos.add(new float[]{startX, startY});

        for (int i = 0; true; i++) {


            if(startX == mX) {
                float y = 0;
                if(mY > startY)
                    y = startY + i;
                else
                    y = startY - i;
                pontos.add(new float[]{startX, y});
                if(y == Math.round(mY))
                    break;
            }
            else if(startY == mY) {
                float x = 0;
                if(mX > startX)
                    x = startX + i;
                else
                    x = startX - i;
                pontos.add(new float[]{x, startY});
                if(x == Math.round(mX))
                    break;
            }
            else {
                if(mX > startX) {
                    float d = (mY - startY)/(mX - startX);
                    cont1++;
                    cont2 += d;
                }
                else{
                    if(mY > startY) {
                        float d = (mY - startY) / (startX - mX);
                        cont1--;
                        cont2 += d;
                    }
                    else{
                        float d = (startY - mY)/(startX - mX);
                        cont1--;
                        cont2 -= d;
                    }
                }
                pontos.add(new float[]{startX + cont1, startY + cont2});
                if (startY + cont2 == mY || startX + cont1 == mX)
                    break;
            }
        }

        int meio = 11;
        for(int i = 0; i < pontos.size(); i++){
            float x = pontos.get(i)[0];
            float x2 = startX + GUI.getGui().getItemTelaDesenhoAutomato().getWidth()/2;
            float x3 = startX - GUI.getGui().getItemTelaDesenhoAutomato().getWidth()/2;
            float y = pontos.get(i)[1];
            float y2 = startY + GUI.getGui().getItemTelaDesenhoAutomato().getWidth()/2;
            float y3 = startY - GUI.getGui().getItemTelaDesenhoAutomato().getWidth()/2;

            if((x == x2 || x == x3 || ((int) x == x2) || ((int) x == x3) || ((int) x == (int) x2) || ((int) x == (int) x3) || ((int) x - 1 == (int) x2) || ((int) x + 1 == (int) x2) || ((int) x - 1 == (int) x3) || ((int) x + 1 == (int) x3)) ||
                    (y == y2 || y == y3 || ((int) y == x2) || ((int) y == y3) || ((int) y == (int) y2) || ((int) y == (int) y3) || ((int) y - 1 == (int) y2) || ((int) y + 1 == (int) y2) || ((int) y - 1 == (int) y3) || ((int) y + 1 == (int) y3))){
                meio = i;
                break;
            }
        }

        startX = pontos.get(meio)[0];
        startY = pontos.get(meio)[1];
        mX = pontos.get(pontos.size() - 1 - meio)[0];
        mY = pontos.get(pontos.size() - 1 - meio)[1];

        int resultado = verificarSetaDupla(transicao);
        int[] cor;
        if(resultado == 0) {
            cor =  new int[]{0,0,0};
            canvas.drawLine(startX, startY, mX, mY, initPaint(cor));
            transicao.setCordenadas_simbolos(new float[]{pontos.get((int)pontos.size()/2)[0], pontos.get((int)pontos.size()/2)[1]});
        }else{
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
            float pointX = (float) (midX + 40 * Math.cos(angleRadians));
            float pointY = (float) (midY + 40 * Math.sin(angleRadians));
            path.moveTo(startX, startY);
            path.cubicTo(startX,startY,pointX, pointY, mX, mY);

            if(resultado == 1) {
                transicao.setCordenadas_simbolos(new float[]{pontos.get(pontos.size() - 1 - meio)[0], pontos.get(pontos.size() - 1 - meio)[1]});
                cor =  new int[]{255,0,0};

            }
            else{
                transicao.setCordenadas_simbolos(new float[]{pontos.get(pontos.size() - 1 - meio)[0], pontos.get(pontos.size() - 1 - meio)[1]});
                cor =  new int[]{0,0,255};
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


