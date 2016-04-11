package com.gestion.rel.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongeez.MongeezRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gestion.rel.domain.Partida;
import com.mongodb.Mongo;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.distribution.Version;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-test.xml" })
public class PartidaRepositoryIntegrationTest {
//
//	private static int port = 37017;
//
//	@Autowired
//	private PartidaRepository partidaRepository;
//    @Autowired private MongoOperations mongoOps;
//
//	@Autowired
//	private MongeezRunner runner;
//
//	@Autowired
//	private Mongo mongo;
//
//	private static MongodExecutable mongoExecutable;
//
//	private static MongodProcess mongoProcess;
//
//	@BeforeClass
//	public static void beforeAllEach() throws Exception {
//		MongodStarter starter = MongodStarter.getDefaultInstance();
//		mongoExecutable = starter.prepare(new MongodConfig(Version.V2_2_0, port, false));
//		mongoProcess = mongoExecutable.start();
//	}
//
//	@AfterClass
//	public static void afterAll() throws Exception {
//		mongoProcess.stop();
//		mongoExecutable.stop();
//	}
//
//	@Before
//	public void beforeEach() throws Exception {
//		runner.setMongo(mongo);
//		runner.execute();
//	}
//
//	@Test
//	public void shouldReadNoteById() {
//		Partida note = this.partidaRepository.getById(1);
//
//		assertNotNull(note);
//		assertEquals(Integer.valueOf(1), note.getId());
//	}
//
//	@Test
//	public void shouldReadAllNotes() {
//		List<Partida> notes = this.partidaRepository.getAll();
//		Assert.assertNotNull(notes);
//		Assert.assertEquals(3, notes.size());
//
//		Iterator<Partida> ite = notes.iterator();
//		while (ite.hasNext()) {
//			Partida note = ite.next();
//			assertNotNull(note);
//			assertFalse(note.getName().isEmpty());
//		}
//	}
//
//	@Test
//	public void shouldReadAllNotesByUser() {
//		String name = "cgpcosmad@gmail.com";
//		List<Partida> notes = this.partidaRepository.getAllByName(name);
//
//		Assert.assertNotNull(notes);
//		Assert.assertEquals(2, notes.size());
//
//		Iterator<Partida> ite = notes.iterator();
//		while (ite.hasNext()) {
//			Partida note = ite.next();
//
//			assertNotNull(note);
//			assertFalse(note.getName().isEmpty());
//			assertEquals(name, note.getName());
//
//		}
//	}
//
//	@Test
//	public void shouldUpdateExistingNote() {
//		Partida note = this.partidaRepository.getById(1);
//		assertEquals("Titulo 1", note.getName());
//		String newTitle = note.getName() + " Updated";
//
//		note.setName(newTitle);
//
//		this.partidaRepository.saveOrUpdate(note);
//
//		Partida note2 = this.partidaRepository.getById(1);
//		assertEquals(newTitle, note2.getName());
//	}
//
//	@Test
//	public void shouldCreateANewNote() {
//		final Integer newId = 10000;
//		long count = this.partidaRepository.getCount();
//
//		Partida nueva = new Partida();
//		nueva.setId(newId);
//		nueva.setName("En un lugar de la mancha");
//		this.partidaRepository.saveOrUpdate(nueva);
//
//		long count2 = this.partidaRepository.getCount();
//		Assert.assertEquals(count + 1, count2);
//
//		Partida note = this.partidaRepository.getById(newId);
//		Assert.assertEquals(nueva, note);
//
//	}

}
