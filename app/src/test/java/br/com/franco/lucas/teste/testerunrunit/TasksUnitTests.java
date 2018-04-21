package br.com.franco.lucas.teste.testerunrunit;

import android.support.v7.app.AppCompatActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.List;

import br.com.franco.lucas.teste.testerunrunit.adapter.TaskAdapter;
import br.com.franco.lucas.teste.testerunrunit.callbacks.OnAdapterClickCallback;
import br.com.franco.lucas.teste.testerunrunit.model.Task;
import br.com.franco.lucas.teste.testerunrunit.presenter.SelectedTaskActivityPresenter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class TasksUnitTests {

    @Mock AppCompatActivity activity;
    @Mock List<Task> list;
    @Mock OnAdapterClickCallback callback;
    SelectedTaskActivityPresenter presenter = new SelectedTaskActivityPresenter();

    @Test
    public void testConvertDate()throws Exception{
        TaskAdapter adapter = new TaskAdapter(activity,list,callback);
        String date = adapter.convertDate(new Date());
        assertNotEquals(date,"23/03/2016");
    }

    @Test
    public void testConvertDateNull()throws Exception{
        String date = presenter.convertDate(null);
        assertThat(date,is(equalTo("")));
    }

    @Test
    public void testConvertSeconds() throws Exception{
        String hours =  presenter.convertTime(28000);
        assertEquals(hours,"07:46");
    }

    @Test
    public void testCheckZeros() throws Exception{
        String formattedString = presenter.checkZeros("7:2");
        assertThat(formattedString, is(equalTo("07:02")));
    }

}